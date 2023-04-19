package com.ajmir.news.model

import java.util.*

data class NewsEntity(
    val title: String,
    val description: String?,
    val content: String?,
    val url: String,
    val imageUrl: String?,
    val publishedAt: Date,
    val author: String,
    val source: String,
)
