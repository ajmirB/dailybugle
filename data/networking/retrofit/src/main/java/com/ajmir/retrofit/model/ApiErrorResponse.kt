package com.ajmir.retrofit.model

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?,
)
