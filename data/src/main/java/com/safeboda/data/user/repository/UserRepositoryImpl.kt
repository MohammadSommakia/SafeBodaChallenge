package com.safeboda.data.user.repository

import androidx.paging.PagingSource
import com.safeboda.data.db.dao.UserDao
import com.safeboda.data.mapper.toDomain
import com.safeboda.data.user.UserApiService
import com.safeboda.domain.models.base.Resource
import com.safeboda.domain.models.user.model.User
import com.safeboda.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getPagedUsersList(
        pageNumber: Int,
        pageSize: Int
    ): Resource<List<User>> {

        return try {
            val response = userApiService.getUsersList(pageNumber = pageNumber, perPage = pageSize)
            if (response.isSuccessful) {
                val body = response.body()?.items?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }

    }

    override suspend fun getAllUsersList(): PagingSource<Int, User> {
        return userDao.getAllUsers()

    }
}