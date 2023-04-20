package com.ajmir.news

import com.ajmir.news.model.ArticleEntity

interface NewsRepository {
    suspend fun getNews(country: String): Result<List<ArticleEntity>>
    fun getArticle(id: String): Result<ArticleEntity>
}
