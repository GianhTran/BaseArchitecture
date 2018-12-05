package com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware

import com.bonseyjaden.basearchitecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor @Inject constructor(userAgent: UserAgent) : ApiInterceptor(userAgent) {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        newRequestBuilder.addHeader(USER_AGENT, buildUserAgent())
        if (BuildConfig.DEBUG) {
            newRequestBuilder.addHeader(
                AUTHORIZATION,
                "$BASIC_AUTHENTICATION $BASIC_AUTHENTICATION_KEY"
            )
        }
        return chain.proceed(newRequestBuilder.build())
    }
}
