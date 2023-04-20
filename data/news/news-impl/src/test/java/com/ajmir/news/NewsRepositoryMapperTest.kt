package com.ajmir.news

import com.ajmir.common.manager.DateManager
import com.ajmir.news.mapper.NewsRepositoryMapper
import com.ajmir.news.remote.model.ArticleResponse
import com.ajmir.news.remote.model.SourceResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class NewsRepositoryMapperTest {

    @MockK
    private lateinit var dateManager: DateManager
    private lateinit var newsRepositoryMapper: NewsRepositoryMapper

    @BeforeEach
    fun setUp() {
        newsRepositoryMapper = NewsRepositoryMapper(dateManager)
        coEvery { dateManager.parse(any(), any()) } returns Date()
    }

    @Test
    fun `mapToEntity maps ArticleResponse to ArticleEntity`() {
        // Given
        val articleResponse = ArticleResponse(
            title = "Test Article",
            description = "This is a test article.",
            content = "Lorem ipsum dolor sit amet.",
            url = "http://example.com/article",
            urlToImage = "http://example.com/image.jpg",
            publishedAt = "2022-03-01T08:00:00Z",
            author = "John Doe",
            source = SourceResponse(id = null, name = "Example News")
        )

        // When
        val articleEntity = newsRepositoryMapper.mapToEntity(articleResponse)

        // Then
        assertEquals(articleResponse.title, articleEntity.title)
        assertEquals(articleResponse.description, articleEntity.description)
        assertEquals(articleResponse.content, articleEntity.content)
        assertEquals(articleResponse.url, articleEntity.url)
        assertEquals(articleResponse.urlToImage, articleEntity.imageUrl)
        assertEquals(articleResponse.author, articleEntity.author)
        assertEquals(articleResponse.source.name, articleEntity.source)

        val expectedPublishedAt = dateManager.parse(articleResponse.publishedAt)!!
        assertEquals(expectedPublishedAt, articleEntity.publishedAt)

        // Check that the ID is a valid UUID
        UUID.fromString(articleEntity.id)
    }
}