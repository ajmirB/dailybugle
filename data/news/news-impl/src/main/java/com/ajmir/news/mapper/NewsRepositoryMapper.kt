package com.ajmir.news.mapper

import com.ajmir.common.manager.DateManager
import com.ajmir.news.model.ArticleEntity
import com.ajmir.news.remote.model.ArticleResponse
import java.util.UUID

class NewsRepositoryMapper(
    private val dateManager: DateManager
) {

    fun mapToEntity(article: ArticleResponse) = ArticleEntity(
        // The id is not provided by the api, we need it to link the news feeds to the article detail
        id = UUID.randomUUID().toString(),
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
