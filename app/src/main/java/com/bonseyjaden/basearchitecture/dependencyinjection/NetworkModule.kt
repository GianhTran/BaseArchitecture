package com.bonseyjaden.basearchitecture.dependencyinjection

import com.bonseyjaden.basearchitecture.BuildConfig
import com.bonseyjaden.basearchitecture.data.repository.remote.api.AuthApi
import com.bonseyjaden.basearchitecture.data.repository.remote.api.NoneAuthApi
import com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware.AuthInterceptor
import com.bonseyjaden.basearchitecture.data.repository.remote.api.middleware.NoneAuthInterceptor
import com.bonseyjaden.basearchitecture.data.repository.remote.api.service.ServiceGenerator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        gson: Gson, authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): AuthApi {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL, AuthApi::class.java, gson,
            null, interceptors
        )
    }

    @Singleton
    @Provides
    fun provideNoneAuthApi(
        gson: Gson, noneAuthInterceptor: NoneAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): NoneAuthApi {
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL, NoneAuthApi::class.java, gson,
            null, interceptors
        )
    }
}