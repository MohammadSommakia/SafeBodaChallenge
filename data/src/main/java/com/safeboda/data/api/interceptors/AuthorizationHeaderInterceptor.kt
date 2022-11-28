package com.safeboda.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * This interceptor adds the Authorization header if the user has non-null token.
 */
class AuthorizationHeaderInterceptor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
//            .header("Authorization", "Bearer ${settingsRepository.getAccessToken()}")
        return chain.proceed(requestBuilder.build())
    }

}