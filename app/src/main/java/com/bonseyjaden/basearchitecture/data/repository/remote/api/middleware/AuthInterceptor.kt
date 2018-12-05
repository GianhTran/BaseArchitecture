package com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware

import com.bonseyjaden.basearchitecture.BuildConfig
import com.bonseyjaden.basearchitecture.data.repository.local.api.AccessTokenWrapper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val accessTokenWrapper: AccessTokenWrapper,
    userAgent: UserAgent
) : ApiInterceptor(userAgent) {

    override fun intercept(chain: Interceptor.Chain): Response {
        accessTokenWrapper.getAccessToken()?.let {
            val originalRequest = chain.request()
            val authorisedRequestBuilder = originalRequest.newBuilder()
            if (BuildConfig.DEBUG) {
                authorisedRequestBuilder.addHeader(
                    DAUTHORIZATION,
                    BEARER_AUTHENTICATION + " " + it.accessToken
                )
                authorisedRequestBuilder.addHeader(
                    AUTHORIZATION,
                    "$BASIC_AUTHENTICATION $BASIC_AUTHENTICATION_KEY"
                )
            } else {
                authorisedRequestBuilder.addHeader(
                    AUTHORIZATION,
                    BEARER_AUTHENTICATION + " " + it.accessToken
                )
            }
            authorisedRequestBuilder.addHeader(USER_AGENT, buildUserAgent())
            return chain.proceed(authorisedRequestBuilder.build())
        }
        throw IllegalArgumentException("access token can not be null")
    }
}