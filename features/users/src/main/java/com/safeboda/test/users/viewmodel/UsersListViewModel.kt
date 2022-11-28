package com.safeboda.test.users.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.safeboda.core.base.BaseViewModel
import com.safeboda.data.db.dao.UserDao
import com.safeboda.data.db.dao.UserKeyDao
import com.safeboda.data.paging.UserRemoteMediator
import com.safeboda.domain.usecases.GetPagedUsersListUseCase
import javax.inject.Inject


class UsersListViewModel @Inject constructor(
    private val getPagedUsersListUseCase: GetPagedUsersListUseCase,
    userDao: UserDao,
    userKeyDao: UserKeyDao

) :
    BaseViewModel<Unit>() {


    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(
            pageSize = 10, initialLoadSize = 1, prefetchDistance = 3,
        ),
        remoteMediator = UserRemoteMediator(
            getPagedUsersListUseCase = getPagedUsersListUseCase,
            userDao = userDao,
            userKeyDao = userKeyDao
        ),
    ) {
        userDao.getAllUsers()
    }.flow.cachedIn(viewModelScope)

}
