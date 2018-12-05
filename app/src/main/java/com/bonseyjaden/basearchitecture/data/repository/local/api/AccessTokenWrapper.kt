package com.bonseyjaden.basearchitecture.data.repository.local.api

import com.bonseyjaden.basearchitecture.data.model.AccessToken

class AccessTokenWrapper constructor(private val sharedPrefApi: SharedPrefApi) {
    private var accessToken: AccessToken? = null

    fun getAccessToken(): AccessToken? {
        // access token can be null in case user not login yet
        if (accessToken == null) {
            accessToken = sharedPrefApi.get(
                SharedPrefApi.PREF_ACCESS_TOKEN,
                AccessToken::class.java
            )
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: AccessToken) {
        this.accessToken = accessToken
        sharedPrefApi.put(SharedPrefApi.PREF_ACCESS_TOKEN, accessToken)
    }

    // TODO This case for test User logout.
    fun deleteAccessToken() {
        accessToken = null
        sharedPrefApi.removeKey(SharedPrefApi.PREF_ACCESS_TOKEN)
    }
}
