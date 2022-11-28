package com.safeboda.domain.usecases

import androidx.paging.PagingSource
import com.safeboda.domain.models.user.model.User
import com.safeboda.domain.repositories.user.UserRepository
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val userRepository: UserRepository) {


    suspend operator fun invoke(): PagingSource<Int, User> =
           userRepository.getAllUsersList()



}

