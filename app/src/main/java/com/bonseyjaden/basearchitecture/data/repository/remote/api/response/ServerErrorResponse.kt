package com.bonseyjaden.basearchitecture.data.repository.remote.api.response

import com.google.gson.annotations.Expose

data class ServerErrorResponse(
    @Expose
    val success: Boolean,
    @Expose
    val errors: List<Error>
) {
    data class Error(
        @Expose
        val code: Int,
        @Expose
        val message: String,
        @Expose
        val resource: String,
        @Expose
        val field: String?
    )
}
