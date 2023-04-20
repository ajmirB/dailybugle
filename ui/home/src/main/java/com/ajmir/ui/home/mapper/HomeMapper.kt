package com.ajmir.ui.home.mapper

import com.ajmir.news.model.ArticleEntity
import com.ajmir.ui.home.model.HomeViewState
import com.ajmir.ui.home.model.NewsViewState

class HomeMapper {

    fun mapToViewState(news: List<ArticleEntity>) = news
        .map {
            NewsViewState(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
                author = it.author,
                source = it.source
            )
        }
        .let {
            HomeViewState.Data(
                news = it
            )
        }
}
