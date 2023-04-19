package com.ajmir.retrofit.mapper

import com.ajmir.retrofit.model.ApiError
import com.ajmir.retrofit.model.ApiErrorResponse

class ApiErrorMapper {

    fun mapError(response: ApiErrorResponse) = ApiError.GenericError(
        code = response.code.orEmpty(),
        message = response.message
    )
}
