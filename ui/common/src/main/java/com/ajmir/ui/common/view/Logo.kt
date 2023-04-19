package com.ajmir.ui.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.ajmir.ui.common.R
import com.ajmir.ui.common.resources.Dimens
import com.ajmir.ui.common.resources.RoundedShape

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .background(Color.Black.copy(alpha = 0.20f))
        .padding(horizontal = Dimens.Spacing.high)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.40f))
                .padding(horizontal = Dimens.Spacing.high)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.60f))
                    .padding(horizontal = Dimens.Spacing.high)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name).uppercase(),
                    color = Color.White,
                    fontSize = Dimens.FontSize.h2,
                    fontWeight = FontWeight.Black,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(horizontal = Dimens.Spacing.high)
                )
            }
        }
    }
}
