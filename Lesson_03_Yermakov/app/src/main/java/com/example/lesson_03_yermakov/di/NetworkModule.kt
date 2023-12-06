package com.example.lesson_03_yermakov.di

import com.example.lesson_03_yermakov.data.NetworkService
import com.example.lesson_03_yermakov.data.interceptors.HeaderInterceptor
import com.example.lesson_03_yermakov.data.repository.PreferenceStorage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_ENDPOINT = "http://45.144.64.179/cowboys/api/"
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        preferenceStorage: PreferenceStorage,
    ) = OkHttpClient.Builder().apply {
        addInterceptor(HeaderInterceptor(preferenceStorage))
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(loggingInterceptor)
    }
        .connectTimeout(20000L, TimeUnit.MILLISECONDS)
        .readTimeout(20000L, TimeUnit.MILLISECONDS)
        .writeTimeout(20000L, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit,
    ): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}