package com.ashish.shgardi.di

import com.ashish.shgardi.data.repository.HomeRepositoryImpl
import com.ashish.shgardi.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
   abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

}