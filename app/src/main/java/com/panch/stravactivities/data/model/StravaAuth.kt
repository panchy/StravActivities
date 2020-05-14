package com.panch.stravactivities.data.model

import com.google.gson.annotations.SerializedName

data class StravaAuth(
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expires_in: Int,
    @SerializedName("expires_at") val expires_at: Long
)