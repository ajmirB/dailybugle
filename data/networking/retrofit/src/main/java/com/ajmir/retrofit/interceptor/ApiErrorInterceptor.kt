package com.ajmir.retrofit.interceptor

import com.ajmir.retrofit.mapper.ApiErrorMapper
import com.ajmir.retrofit.model.ApiError
import com.ajmir.retrofit.model.ApiErrorResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response


class ApiErrorInterceptor(
    private val gson: Gson,
    private val apiErrorMapper: ApiErrorMapper
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            val errorBody = response.body?.string().orEmpty()
            val errorResponse = gson.fromJson(errorBody, ApiErrorResponse::class.java)
            throw apiErrorMapper.mapError(errorResponse)
        }
        return response
    }
}
