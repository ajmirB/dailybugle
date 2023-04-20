package com.ajmir.ui.article.model

sealed interface ArticleViewState {

    object Loading: ArticleViewState

    object Error: ArticleViewState

    data class Data(
        val id: String,
        val title: String,
        val imageUrl: String?,
        val description: String?,
        val url: String,
        val author: String?,
        val source: String
    ): ArticleViewState
}

