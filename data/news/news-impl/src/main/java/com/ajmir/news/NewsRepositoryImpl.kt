package com.ajmir.news

import com.ajmir.news.mapper.NewsRepositoryMapper
import com.ajmir.news.model.NewsEntity
import com.ajmir.news.remote.NewsApi

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val mapper: NewsRepositoryMapper
): NewsRepository {

    override suspend fun getNews(): Result<List<NewsEntity>> {
        return runCatching {
            newsApi.getTopHeadlines("fr")
                .articles
                .map(mapper::mapToEntity)
        }
    }
}
