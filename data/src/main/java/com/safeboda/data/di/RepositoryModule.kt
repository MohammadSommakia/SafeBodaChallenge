package com.safeboda.data.di

import com.safeboda.data.user.repository.UserRepositoryImpl
import com.safeboda.domain.repositories.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}