package com.safeboda.domain.repositories.user

import androidx.paging.PagingSource
import com.safeboda.domain.models.base.Resource
import com.safeboda.domain.models.user.model.User

interface UserRepository {

    suspend fun getPagedUsersList(
        pageNumber: Int,
        pageSize: Int,
    ): Resource<List<User>>

    suspend fun getAllUsersList(): PagingSource<Int, User>

}