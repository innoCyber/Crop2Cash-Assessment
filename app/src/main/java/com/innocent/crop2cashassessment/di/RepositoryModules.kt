package com.innocent.crop2cashassessment.di

import com.innocent.crop2cashassessment.model.ExhibitsLoader
import com.innocent.crop2cashassessment.restExhibitsLoader.ExhibitsLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {
    @Binds
    fun provideCollectionRepo(repositoryImpl: ExhibitsLoaderImpl): ExhibitsLoader

}