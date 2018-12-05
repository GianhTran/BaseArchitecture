package com.bonseyjaden.basearchitecture.data.repository

import com.bonseyjaden.basearchitecture.data.model.Data
import com.bonseyjaden.basearchitecture.data.repository.local.DataLocalDataSource
import com.bonseyjaden.basearchitecture.data.repository.remote.DataRemoteDataSource

class DataRepository constructor(
    private val dataLocalDataSource: DataLocalDataSource,
    private val dataRemoteDataSource: DataRemoteDataSource
) : BaseRepository() {
    fun getData(): Data {
        val data = dataLocalDataSource.getData()
        data?.let {
            return it
        }
        return dataRemoteDataSource.fetchData()
    }
}