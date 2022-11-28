package com.safeboda.data.user

import com.safeboda.data.user.model.UserResponse
import com.safeboda.data.user.model.UsersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @GET("/search/users?q=repos:>1")
    suspend fun getUsersList(
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int
    ): Response<UsersListResponse>

    @GET("users/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): Response<UserResponse>
}