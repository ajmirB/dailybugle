package com.ajmir.ui.article.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.ajmir.ui.article.model.ArticleViewState
import com.ajmir.ui.common.R
import com.ajmir.ui.common.resources.Colors
import com.ajmir.ui.common.resources.Dimens
import com.ajmir.ui.common.view.OwnerTagsView

@Composable
fun ArticleDataView(
    viewState: ArticleViewState.Data
) {
    val uriHandler = LocalUriHandler.current
    val scrollableState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium),
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary)
            .scrollable(scrollableState, Orientation.Vertical),
    ) {
        // Image
        SubcomposeAsyncImage(
            model = viewState.imageUrl,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Black, BlendMode.Saturation),
            contentScale = ContentScale.FillWidth,
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty,
                is AsyncImagePainter.State.Error ->
                    Spacer(modifier = Modifier.height(60.dp))
                is AsyncImagePainter.State.Loading ->
                    CircularProgressIndicator()
                is AsyncImagePainter.State.Success ->
                    SubcomposeAsyncImageContent()
            }
        }
        // Use a second column inside to create a padding for the text only
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium),
            modifier = Modifier.padding(horizontal = Dimens.Spacing.small)
        ) {
            // Title
            Text(
                text = viewState.title,
                fontSize = Dimens.FontSize.h2,
                color = Colors.background,
            )
            // Tags
            OwnerTagsView(
                source = viewState.source,
                author = viewState.author
            )
            // Description
            viewState.description?.let { description ->
                Text(
                    text = description,
                    color = Colors.background.copy(alpha = 0.8f)
                )
            }
            // Button to open the url
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.background,
                    contentColor = Colors.primary
                ),
                shape = RectangleShape,
                onClick = { uriHandler.openUri(viewState.url) }
            ) {
                Text(text = stringResource(id = R.string.article_open_button))
            }
        }
    }
}