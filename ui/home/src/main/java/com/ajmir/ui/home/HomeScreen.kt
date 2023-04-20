package com.ajmir.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ajmir.ui.common.view.AppBar
import com.ajmir.ui.common.view.ErrorView
import com.ajmir.ui.common.view.LoadingView
import com.ajmir.ui.home.model.HomeViewState
import com.ajmir.ui.home.view.HomeDataView
import com.ajmir.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onArticleClicked: (String) -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>()

    val viewState by viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (viewState) {
            is HomeViewState.Data ->
                HomeDataView(
                    viewState = viewState as HomeViewState.Data,
                    onArticleClicked = onArticleClicked
                )
            HomeViewState.Error ->
                ErrorView(onRetry = viewModel::onRetryClicked)
            HomeViewState.Loading ->
                LoadingView()
        }
        AppBar()
    }
}
