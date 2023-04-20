package com.ajmir.news.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val articles: List<ArticleResponse>,
    @SerializedName("totalResults") val totalResults: Int,
)

data class ArticleResponse(
    val source: SourceResponse,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class SourceResponse(
    val id: String?,
    val name: String,
)
