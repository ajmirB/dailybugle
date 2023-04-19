package com.ajmir.retrofit.interceptor

import com.ajmir.retrofit.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor: Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            return chain.proceed(
                request.newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, BuildConfig.API_KEY)
                    .build()
            )
        }

        companion object {
            private const val HEADER_AUTHORIZATION = "Authorization"
        }
    }
