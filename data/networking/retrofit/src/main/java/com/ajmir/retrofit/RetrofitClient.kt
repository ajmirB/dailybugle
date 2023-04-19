package com.ajmir.retrofit

import com.ajmir.retrofit.interceptor.ApiErrorInterceptor
import com.ajmir.retrofit.interceptor.ApiKeyInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    fun provideOkhttp(
        apiKeyInterceptor: ApiKeyInterceptor,
        apiErrorInterceptor: ApiErrorInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                // Add logging interceptor on debug mode only
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
            addInterceptor(apiKeyInterceptor)
            addInterceptor(apiErrorInterceptor)
        }.build()
    }

    fun provideClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
