package com.bonseyjaden.basearchitecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessToken(
    @Expose
    var id: String,

    @Expose
    @SerializedName("access_token")
    var accessToken: String,

    @Expose
    @SerializedName("token_type")
    val tokenType: String,

    @Expose
    @SerializedName("refresh_token")
    var refreshToken: String,

    @Expose
    @SerializedName("expires_in")
    var expiresIn: Long,

    @Expose
    val scope: String,

    @Expose
    @SerializedName("created_at")
    val createdAt: String,

    @Expose
    @SerializedName("expires_on")
    val expiresOn: String
)