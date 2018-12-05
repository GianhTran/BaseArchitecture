package com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware

import com.bonseyjaden.basearchitecture.BuildConfig

class UserAgent {
    fun getAppVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getAppId(): String {
        return ""
    }
}