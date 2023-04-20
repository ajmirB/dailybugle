package com.ajmir.news

import com.ajmir.news.mapper.NewsRepositoryMapper
import com.ajmir.news.model.ArticleEntity
import com.ajmir.news.remote.NewsApi
import com.ajmir.news.remote.model.*
import com.ajmir.retrofit.model.ApiError
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
internal class NewsRepositoryImplTest {

    @MockK
    private lateinit var api: NewsApi

    @MockK
    private lateinit var mapper: NewsRepositoryMapper

    private lateinit var repository: NewsRepositoryImpl

    @BeforeEach
    internal fun setUp() {
        repository = NewsRepositoryImpl(api, mapper)

        coEvery { mapper.mapToEntity(any()) } returns ArticleEntity(
            title = "Morgan Stanley tops analysts’ expectations on better-than-expected trading results - CNBC",
            description = "Morgan Stanley gets most of its revenue from wealth and investment management, steadier businesses that helped offset volatile trading and advisory results.",
            url = "",
            imageUrl = null,
            publishedAt = Date(),
            content = "Morgan Stanley on Wednesday topped estimates for first quarter profit and revenue on better-than-expected trading results.\\r\\nHere's how the company did:\\r\\n<ul><li>Earnings of \$1.70 per share, vs. \$1.62… [+2830 chars]",
            author = "Hugh Son",
            source = "cnn"
        )
    }

    @Test
    fun `getNews, with success value should return an entity`() {
        // Given
        val newsResponse = NewsResponse(
            totalResults = 2,
            articles = listOf(
                ArticleResponse(
                    source = SourceResponse("cnn", name = "CNN"),
                    author = "Tierney Sneed",
                    title = "What to watch for with the Supreme Court and medication abortion on Wednesday - CNN",
                    description = "All eyes are on the Supreme Court as it could say at any moment how it will handle a blockbuster medication abortion case that landed on its doorstep late last week.",
                    url = "",
                    urlToImage = null,
                    publishedAt = "2023-04-19T12:05:00Z",
                    content = null
                ),
                ArticleResponse(
                    source = SourceResponse(null, name = "CNBC"),
                    author = "Hugh Son",
                    title = "Morgan Stanley tops analysts’ expectations on better-than-expected trading results - CNBC",
                    description = "Morgan Stanley gets most of its revenue from wealth and investment management, steadier businesses that helped offset volatile trading and advisory results.",
                    url = "",
                    urlToImage = null,
                    publishedAt = "2023-04-19T11:35:41Z",
                    content = "Morgan Stanley on Wednesday topped estimates for first quarter profit and revenue on better-than-expected trading results.\\r\\nHere's how the company did:\\r\\n<ul><li>Earnings of \$1.70 per share, vs. \$1.62… [+2830 chars]"
                )
            )
        )
        coEvery { api.getTopHeadlines(any()) } returns newsResponse

        // When
        val result = runBlocking { repository.getNews() }

        // Then
        result.isSuccess shouldBe true
        assert(result.getOrNull() is List<ArticleEntity>)
    }

    @Test
    fun `getNews, error thrown by api, should not crash`() {
        // Given
        coEvery { api.getTopHeadlines(any()) } throws ApiError.GenericError("","")

        // When
        val result = runBlocking { repository.getNews() }

        // Then
        result.isFailure shouldBe true
        assert(result.exceptionOrNull() is ApiError.GenericError)
    }

    @Test
    fun `getNews, error thrown in mapper, should not crash`() {
        // Given
        val error = Error("invalid mapping")
        val newsResponse = NewsResponse(
            totalResults = 2,
            articles = listOf(
                ArticleResponse(
                    source = SourceResponse("cnn", name = "CNN"),
                    author = "Tierney Sneed",
                    title = "What to watch for with the Supreme Court and medication abortion on Wednesday - CNN",
                    description = "All eyes are on the Supreme Court as it could say at any moment how it will handle a blockbuster medication abortion case that landed on its doorstep late last week.",
                    url = "",
                    urlToImage = null,
                    publishedAt = "2023-04-19T12:05:00Z",
                    content = null
                ),
                ArticleResponse(
                    source = SourceResponse(null, name = "CNBC"),
                    author = "Hugh Son",
                    title = "Morgan Stanley tops analysts’ expectations on better-than-expected trading results - CNBC",
                    description = "Morgan Stanley gets most of its revenue from wealth and investment management, steadier businesses that helped offset volatile trading and advisory results.",
                    url = "",
                    urlToImage = null,
                    publishedAt = "2023-04-19T11:35:41Z",
                    content = "Morgan Stanley on Wednesday topped estimates for first quarter profit and revenue on better-than-expected trading results.\\r\\nHere's how the company did:\\r\\n<ul><li>Earnings of \$1.70 per share, vs. \$1.62… [+2830 chars]"
                )
            )
        )
        coEvery { api.getTopHeadlines(any()) } returns newsResponse
        coEvery { mapper.mapToEntity(any()) } throws error

        // When
        val result = runBlocking { repository.getNews() }

        // Then
        result.isFailure shouldBe true
        result.exceptionOrNull() shouldBe error
    }
}
