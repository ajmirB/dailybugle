package com.ajmir.news.mapper

import com.ajmir.common.manager.DateManager
import com.ajmir.news.model.NewsEntity
import com.ajmir.news.remote.model.ArticleResponse
import com.ajmir.news.remote.model.NewsResponse
import com.ajmir.retrofit.model.ApiError
import com.ajmir.retrofit.model.ApiErrorResponse

class NewsRepositoryMapper(
    private val dateManager: DateManager
) {

    fun mapToEntity(article: ArticleResponse) = NewsEntity(
        title = article.title,
        description = article.description,
        content = article.content,
        url = article.url,
        imageUrl = article.urlToImage,
        publishedAt = dateManager.parse(article.publishedAt)!!,
        author = article.author,
        source = article.source.name
    )
}
