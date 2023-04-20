package com.ajmir.news

import android.util.Log
import com.ajmir.news.mapper.NewsRepositoryMapper
import com.ajmir.news.model.NewsDetailNotFound
import com.ajmir.news.model.ArticleEntity
import com.ajmir.news.remote.NewsApi

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val mapper: NewsRepositoryMapper
): NewsRepository {

    private var lastFetchedNews: List<ArticleEntity> = emptyList()

    override suspend fun getNews(country: String): Result<List<ArticleEntity>> {
        return runCatching {
            newsApi.getTopHeadlines(country)
                .articles
                .map(mapper::mapToEntity)
                .also { lastFetchedNews = it }
        }
    }

    override fun getArticle(id: String): Result<ArticleEntity> {
        return lastFetchedNews.firstOrNull { it.id == id }
            ?.let { Result.success(it) }
            ?: Result.failure(NewsDetailNotFound)
    }
}
