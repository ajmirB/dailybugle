package com.ajmir.retrofit

import com.ajmir.retrofit.interceptor.ApiErrorInterceptor
import com.ajmir.retrofit.interceptor.ApiKeyInterceptor
import com.ajmir.retrofit.mapper.ApiErrorMapper
import com.google.gson.Gson
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val networkingModule = module {
    factoryOf(::Gson)
    factoryOf(::ApiKeyInterceptor)
    factoryOf(::ApiErrorInterceptor)
    factoryOf(::ApiErrorMapper)
    factoryOf(RetrofitProvider::provideOkhttp)
    factory {
        RetrofitProvider.provideClient(
            baseUrl = BuildConfig.BASE_URL,
            gson = get(),
            okHttpClient = get()
        )
    }
}
