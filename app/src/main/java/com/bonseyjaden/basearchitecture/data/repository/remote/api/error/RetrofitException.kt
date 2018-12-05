package com.bonseyjaden.basearchitecture.data.repository.remote.api.error

import com.bonseyjaden.basearchitecture.data.repository.remote.api.response.ServerErrorResponse
import retrofit2.Response
import java.io.IOException

class RetrofitException(
    private val kind: Kind,
    private val serverErrorResponse: ServerErrorResponse? = null,
    private val response: Response<*>? = null,
    private val exception: Throwable? = null
) : RuntimeException() {

    companion object {
        fun httpError(response: Response<*>): RetrofitException {
            return RetrofitException(Kind.HTTP, null, response)
        }

        fun serverError(serverErrorResponse: ServerErrorResponse): RetrofitException {
            return RetrofitException(Kind.SERVER, serverErrorResponse)
        }

        fun networkError(throwable: Throwable): RetrofitException {
            return RetrofitException(Kind.NETWORK, null, null, throwable)
        }

        fun unexpectedError(throwable: Throwable): RetrofitException {
            return RetrofitException(Kind.NETWORK, null, null, throwable)
        }
    }

    fun getServerErrors(): List<ServerErrorResponse.Error>? {
        return if (kind == Kind.SERVER && serverErrorResponse != null) {
            serverErrorResponse.errors
        } else null
    }

    fun getFirstServerMessage(): String? {
        return if (kind == Kind.SERVER && serverErrorResponse != null && serverErrorResponse.errors.isNotEmpty()) {
            serverErrorResponse.errors[0].message
        } else null
    }

    fun isNetworkError(): Boolean {
        return kind == Kind.NETWORK
    }

    fun isServerError(): Boolean {
        return kind == Kind.SERVER
    }

    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}