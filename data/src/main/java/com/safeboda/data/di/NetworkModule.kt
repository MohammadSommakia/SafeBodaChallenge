package com.safeboda.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.safeboda.core.data.BuildConfig
import com.safeboda.data.api.interceptors.AuthorizationHeaderInterceptor
import com.safeboda.domain.scopes.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This module contains the provide methods for retrofit & OkHttp
 */
@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideOkHttpClient(
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.addInterceptor(authorizationHeaderInterceptor)
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }



    @AppScope
    @Provides
    fun provideAuthorizationHeaderInterceptor(): AuthorizationHeaderInterceptor {
        return AuthorizationHeaderInterceptor()
    }

    @AppScope
    @Provides
    fun provideGithubGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .serializeNulls()
            .create()
    }


    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
}
