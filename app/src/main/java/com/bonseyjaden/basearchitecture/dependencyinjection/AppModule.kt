package com.bonseyjaden.basearchitecture.dependencyinjection

import android.app.Application
import android.content.Context
import com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware.UserAgent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideUserAgent(): UserAgent {
        return UserAgent()
    }
}