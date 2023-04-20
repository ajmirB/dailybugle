package com.ajmir.ui.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ajmir.ui.article.model.ArticleViewState
import com.ajmir.ui.article.view.ArticleDataView
import com.ajmir.ui.article.viewmodel.ArticleViewModel
import com.ajmir.ui.common.resources.Colors
import com.ajmir.ui.common.resources.Dimens
import com.ajmir.ui.common.view.ErrorView
import com.ajmir.ui.common.view.LoadingView
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticleScreen(
    id: String,
    onBack: () -> Unit
) {
    val viewModel = getViewModel<ArticleViewModel>()

    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onLoadArticle(id)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        when (viewState) {
            is ArticleViewState.Data ->
                ArticleDataView(viewState = viewState as ArticleViewState.Data)
            ArticleViewState.Error ->
                ErrorView(onRetry = viewModel::onRetryClicked)
            ArticleViewState.Loading ->
                LoadingView()
        }
        Box(Modifier
            .padding(Dimens.Spacing.medium)
            .background(Colors.primary)
            .clickable(onClick = onBack)
            .padding(Dimens.Spacing.small)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = Colors.background
            )
        }
    }
}
