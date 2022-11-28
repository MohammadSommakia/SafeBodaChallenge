package com.safeboda.domain.usecases

import com.safeboda.domain.models.base.Resource
import com.safeboda.domain.models.user.model.User
import com.safeboda.domain.repositories.user.UserRepository
import com.safeboda.domain.usecases.base.BaseUseCase
import com.safeboda.domain.usecases.base.UseCaseParameters
import javax.inject.Inject


class GetPagedUsersListUseCase @Inject constructor(private val userRepository: UserRepository) :
    BaseUseCase<Resource<List<User>>, GetPagedUsersListUseCase.Params>() {


    data class Params(
        val pageNumber: Int,
        val pageSize: Int,
    ) : UseCaseParameters

    override suspend fun invoke(params: Params): Resource<List<User>> {
        return userRepository.getPagedUsersList(params.pageNumber, params.pageSize)
    }
}
