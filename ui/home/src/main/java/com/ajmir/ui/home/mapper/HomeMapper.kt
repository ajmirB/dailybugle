package com.ajmir.ui.home.mapper

import com.ajmir.news.model.NewsEntity
import com.ajmir.ui.home.model.HomeViewState
import com.ajmir.ui.home.model.NewsViewState

class HomeMapper {

    fun mapToViewState(news: List<NewsEntity>) = news
        .map {
            NewsViewState(
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
