package com.safeboda.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.safeboda.data.db.UserKey
import com.safeboda.data.db.dao.UserDao
import com.safeboda.data.db.dao.UserKeyDao
import com.safeboda.domain.models.base.Resource
import com.safeboda.domain.models.user.model.User
import com.safeboda.domain.usecases.GetPagedUsersListUseCase
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val getPagedUsersListUseCase: GetPagedUsersListUseCase,
    private val userDao: UserDao,
    private val userKeyDao: UserKeyDao
) : RemoteMediator<Int, User>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {

        return try {

            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.next
                        ?: throw InvalidObjectException("Remote key should not be null for $loadType")

                    nextKey
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    val prevKey = remoteKeys?.prev
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    prevKey
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next?.minus(1) ?: 1

                }
            }

            val response = getPagedUsersListUseCase(
                GetPagedUsersListUseCase.Params(
                    pageNumber = page,
                    pageSize = state.config.pageSize
                )
            )
            val endOfPagination = response.data?.size!! < state.config.pageSize

            when (response) {
                is Resource.Success -> {
                    val body = response.data

                    if (loadType == LoadType.REFRESH) {
                        userKeyDao.deleteAllBlogKey()
                        userDao.deleteAllUsers()
                    }

                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1

                    val list = body?.map {
                        UserKey(id = it.id, prev, next)
                    }
                    list?.let { userKeyDao.insertAllUsersKeys(list) }
                    body?.let { userDao.insertAllUsers(body) }


                }
                is Resource.Error -> {
                    MediatorResult.Error(Exception())

                }

                else -> {}
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
        catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, User>): UserKey? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                userKeyDao.getAllKeys(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, User>): UserKey? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                userKeyDao.getAllKeys(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, User>
    ): UserKey? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                userKeyDao.getAllKeys(repoId)
            }
        }
    }


}













