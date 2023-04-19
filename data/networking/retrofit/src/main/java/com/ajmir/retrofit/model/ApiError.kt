package com.ajmir.retrofit.model

sealed interface ApiError {
    data class GenericError(
        val code: String,
        override val message: String?
    ): Error(), ApiError
}
