package com.ajmir.ui.article.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajmir.news.NewsRepository
import com.ajmir.ui.article.mapper.ArticleMapper
import com.ajmir.ui.article.model.ArticleViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class ArticleViewModel(
    private val newsRepository: NewsRepository,
    private val mapper: ArticleMapper
): ViewModel() {

    var viewState = MutableStateFlow<ArticleViewState>(ArticleViewState.Loading)
        private set

    private lateinit var articleId: String

    // region User Interaction

    fun onLoadArticle(id: String) {
        articleId = id
        viewModelScope.launch {
            newsRepository.getArticle(id)
                .onSuccess { article ->
                    viewState.update {
                        mapper.mapToViewState(article)
                    }
                }
                .onFailure {
                    viewState.update { ArticleViewState.Error }
                }
        }
    }

    fun onRetryClicked() {
        viewState.update { ArticleViewState.Loading }
        onLoadArticle(articleId)
    }

    // endregion
}
