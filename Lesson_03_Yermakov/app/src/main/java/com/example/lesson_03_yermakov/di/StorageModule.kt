package com.example.lesson_03_yermakov.di

import android.content.Context
import androidx.room.Room
import com.example.lesson_03_yermakov.data.NetworkService
import com.example.lesson_03_yermakov.data.cache.AppDatabase
import com.example.lesson_03_yermakov.data.cache.CatalogDAO
import com.example.lesson_03_yermakov.data.datasource.CacheCatalogDataSource
import com.example.lesson_03_yermakov.data.datasource.CloudCatalogDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    fun provideRoomDatabase(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()

    @Provides
    fun provideCatalogDao(db: AppDatabase): CatalogDAO = db.catalogDao()

    @Provides
    @Singleton
    fun provideCacheDataSource(dao: CatalogDAO): CacheCatalogDataSource =
        CacheCatalogDataSource(dao)

    @Provides
    @Singleton
    fun provideCloudDataSource(networkService: NetworkService): CloudCatalogDataSource =
        CloudCatalogDataSource(networkService)
}