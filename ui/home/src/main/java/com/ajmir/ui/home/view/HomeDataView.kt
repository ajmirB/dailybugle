package com.ajmir.ui.home.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ajmir.ui.common.resources.Colors
import com.ajmir.ui.common.resources.Dimens
import com.ajmir.ui.common.resources.RoundedShape
import com.ajmir.ui.home.model.HomeViewState

@Composable
fun HomeDataView(
    data: HomeViewState.Data
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 35.dp)
    ) {
        data.news.forEach {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium),
                    modifier = Modifier.padding(Dimens.Spacing.high)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Tag(text = it.author.uppercase(), backgroundColor = Colors.primary)
                        Tag(text = it.source.uppercase(), backgroundColor = Colors.secondary)
                    }
                    Row {
                        it.imageUrl?.let { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        Text(it.title)
                    }
                }
            }
        }
    }
}

@Composable
private fun Tag(
    text: String,
    textColor: Color = Colors.tag,
    backgroundColor: Color = Color.Black
) {
    Text(
        text = text.uppercase(),
        fontSize = 10.sp,
        color = textColor,
        modifier = Modifier
            .background(backgroundColor, RoundedShape)
            .padding(5.dp)
    )
}
