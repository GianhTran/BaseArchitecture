package com.bonseyjaden.basearchitecture.data.repository.remote

import com.bonseyjaden.basearchitecture.data.model.Data
import com.bonseyjaden.basearchitecture.data.repository.remote.api.AuthApi
import com.bonseyjaden.basearchitecture.data.repository.remote.api.NoneAuthApi
import javax.inject.Inject

class DataRemoteDataSource @Inject constructor(
    private val authApi: AuthApi,
    private val noneAuthApi: NoneAuthApi
) {
    fun fetchData(): Data {
        // todo fetch data from remote, using authApi or noneAuthApi
        return Data("remote key")
    }
}