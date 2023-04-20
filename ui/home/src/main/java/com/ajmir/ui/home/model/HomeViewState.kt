package com.ajmir.ui.home.model

import com.ajmir.ui.home.viewmodel.HomeViewModel

sealed interface HomeViewState {
    object Loading: HomeViewState

    object Error: HomeViewState

    data class Data(
        val news: List<NewsViewState>
    ): HomeViewState
}

data class NewsViewState(
    val id: String,
    val title: String,
    val imageUrl: String?,
    val author: String?,
    val source: String
)
