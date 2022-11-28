package com.safeboda.data.di

import com.safeboda.data.user.UserApiService
import com.safeboda.domain.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * This module has the API services to fetch the data
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

    @AppScope
    @Provides
    fun provideGithubApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }



}