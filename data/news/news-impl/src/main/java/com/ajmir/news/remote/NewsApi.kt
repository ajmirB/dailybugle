package com.ajmir.news.remote

import com.ajmir.news.remote.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ) : NewsResponse
}

fun provideNewsApi(client: Retrofit): NewsApi = client.create(NewsApi::class.java)
