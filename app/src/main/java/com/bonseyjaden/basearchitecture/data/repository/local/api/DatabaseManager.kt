package com.bonseyjaden.basearchitecture.data.repository.local.api

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bonseyjaden.basearchitecture.data.model.Data
import com.bonseyjaden.basearchitecture.data.repository.local.api.DatabaseManager.Companion.DATABASE_VERSION
import com.bonseyjaden.basearchitecture.data.repository.local.api.dao.DataDao

@Database(entities = [Data::class], version = DATABASE_VERSION)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        const val DATABASE_NAME = "BaseArchitectureDB"
        const val DATABASE_VERSION = 1
    }
}
