package com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

const val AUTHORIZATION = "Authorization"
const val DAUTHORIZATION = "DAuthorization"
const val USER_AGENT = "X-User-Agent"
const val DEVICE = "android"
const val BASIC_AUTHENTICATION = "Basic"
const val BEARER_AUTHENTICATION = "Bearer"
const val BASIC_AUTHENTICATION_KEY = "ZGV2ZWxvcC1henVpOks0WTZkTmEjKTI/dF45I1E="

open class ApiInterceptor @Inject constructor(private val userAgent: UserAgent) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(buildRequest(chain))
    }

    open fun buildRequest(chain: Interceptor.Chain): Request {
        return chain.request()
    }

    internal fun buildUserAgent(): String {
        return DEVICE + " " + userAgent.getAppVersion() + " " + userAgent.getAppId()
    }
}