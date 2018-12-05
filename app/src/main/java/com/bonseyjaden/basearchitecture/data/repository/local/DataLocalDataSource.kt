package com.bonseyjaden.basearchitecture.data.repository.local

import com.bonseyjaden.basearchitecture.data.model.Data
import com.bonseyjaden.basearchitecture.data.repository.local.api.DatabaseApi
import javax.inject.Inject

class DataLocalDataSource @Inject constructor(private val databaseApi: DatabaseApi) : BaseLocalDataSource() {
    fun getData(): Data? {
        // todo get data from database, using databaseApi
        return Data("local key")
    }
}