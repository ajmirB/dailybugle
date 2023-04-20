package com.ajmir.ui.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.ajmir.ui.common.resources.Dimens
import com.ajmir.ui.common.view.OwnerTagsView
import com.ajmir.ui.home.model.HomeViewState

@Composable
fun HomeDataView(
    viewState: HomeViewState.Data,
    onArticleClicked: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 55.dp)
    ) {
        viewState.news.forEach { article ->
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.small),
                    modifier = Modifier
                        .clickable { onArticleClicked(article.id) }
                        .padding(horizontal = Dimens.Spacing.small)
                        .padding(bottom = Dimens.Spacing.high + Dimens.Spacing.verySmall)
                ) {
                    // Header: Owner
                    OwnerTagsView(
                        source = article.source,
                        author = article.author
                    )
                    // Content
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing.small)
                    ) {
                        // Image
                        SubcomposeAsyncImage(
                            model = article.imageUrl,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Black, BlendMode.Saturation),
                            contentScale = ContentScale.Crop,
                        ) {
                            when (painter.state) {
                                AsyncImagePainter.State.Empty,
                                is AsyncImagePainter.State.Error -> { /* Nothing */}
                                is AsyncImagePainter.State.Loading ->
                                    Spacer(
                                        modifier = Modifier.size(Dimens.imageThumbnail)
                                    )
                                is AsyncImagePainter.State.Success ->
                                    SubcomposeAsyncImageContent(
                                        modifier = Modifier.size(Dimens.imageThumbnail)
                                    )
                            }
                        }
                        // Text
                        Text(article.title)
                    }
                }
            }
        }
    }
}
