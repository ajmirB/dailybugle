package com.ajmir.retrofit.interceptor

import androidx.annotation.VisibleForTesting
import com.ajmir.retrofit.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor(
    private val apiKey: String
): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            addHeader(request)
        )
    }

    @VisibleForTesting
    fun addHeader(request: Request): Request {
        return request.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, apiKey)
            .build()
    }

    companion object {
        @VisibleForTesting
        const val HEADER_AUTHORIZATION = "Authorization"
    }
}
