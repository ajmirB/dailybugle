package com.ajmir.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajmir.news.NewsRepository
import com.ajmir.ui.home.mapper.HomeMapper
import com.ajmir.ui.home.model.HomeViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val newsRepository: NewsRepository,
    private val homeMapper: HomeMapper
): ViewModel() {

    var viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
        private set

    init {
        loadData()
    }

    // region User Interaction

    fun onRetryClicked() {
        viewState.update { HomeViewState.Loading }
        loadData()
    }

    // endregion

    private fun loadData() {
        viewModelScope.launch {
            newsRepository.getNews(country = Locale.getDefault().country)
                .onSuccess { state ->
                    viewState.update {
                        homeMapper.mapToViewState(state)
                    }
                }
                .onFailure {
                    viewState.update { current ->
                        if (current is HomeViewState.Data) current
                        else HomeViewState.Error
                    }
                }
        }
    }
}
