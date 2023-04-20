package com.ajmir.ui.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ajmir.ui.common.resources.Colors
import com.ajmir.ui.common.resources.Dimens

@Composable
fun OwnerTagsView(
    source: String,
    author: String? = null
) {
    Row(
        Modifier.border(Dimens.border, Colors.background)
    ) {
        Tag(
            text = source.uppercase(),
            backgroundColor = Colors.primary
        )
        author
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                Tag(
                    text = it.uppercase(),
                    backgroundColor = Colors.secondary
                )
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
        fontSize = Dimens.FontSize.h4,
        color = textColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .background(backgroundColor)
            .padding(
                horizontal = Dimens.Spacing.small,
                vertical = 3.dp
            )
    )
}