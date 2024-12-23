package com.ashish.shgardi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun provideCacheSize(): Long {
        return 10 * 1024 * 1024 // 10 MB cache size
    }


}