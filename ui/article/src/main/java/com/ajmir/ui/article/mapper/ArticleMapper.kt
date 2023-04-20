package com.ajmir.ui.article.mapper

import com.ajmir.news.model.ArticleEntity
import com.ajmir.ui.article.model.ArticleViewState

class ArticleMapper {

    fun mapToViewState(article: ArticleEntity) =
        ArticleViewState.Data(
            id = article.id,
            title = article.title,
            imageUrl = article.imageUrl,
            description = article.description,
            url = article.url,
            author = article.author,
            source = article.source
        )
}
