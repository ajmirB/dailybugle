package com.ajmir.news

import com.ajmir.news.model.NewsEntity

interface NewsRepository {
    suspend fun getNews(): Result<List<NewsEntity>>
}
