package com.bonseyjaden.basearchitecture.dependencyinjection

import android.arch.persistence.room.Room
import android.content.Context
import com.bonseyjaden.basearchitecture.data.repository.DataRepository
import com.bonseyjaden.basearchitecture.data.repository.local.DataLocalDataSource
import com.bonseyjaden.basearchitecture.data.repository.local.api.*
import com.bonseyjaden.basearchitecture.data.repository.remote.DataRemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context, gson: Gson): SharedPrefApi {
        return SharedPrefApi(context, gson)
    }

    @Singleton
    @Provides
    fun provideAccessTokenWrapper(sharedPrefApi: SharedPrefApi): AccessTokenWrapper {
        return AccessTokenWrapper(sharedPrefApi)
    }

    @Singleton
    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return Room.databaseBuilder(
            context, DatabaseManager::class.java,
            DatabaseManager.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseApi(databaseManager: DatabaseManager): DatabaseApi {
        return DatabaseApiImpl(databaseManager)
    }

    @Singleton
    @Provides
    fun provideDataRepository(
        localDataSource: DataLocalDataSource,
        remoteDataSource: DataRemoteDataSource
    ): DataRepository {
        return DataRepository(localDataSource, remoteDataSource)
    }
}