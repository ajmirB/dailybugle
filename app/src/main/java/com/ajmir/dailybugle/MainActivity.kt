package com.ajmir.dailybugle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.ajmir.ui.common.resources.Colors
import com.ajmir.ui.common.resources.DailyBugleTheme
import com.ajmir.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBugleTheme {
                Box(
                    Modifier.fillMaxSize()
                        .background(Colors.background)
                ) {
                    HomeScreen(onArticleClicked = {})
                }
            }
        }
    }
}
