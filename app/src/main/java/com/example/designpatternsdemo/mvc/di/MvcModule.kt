package com.example.designpatternsdemo.mvc.di

import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.mvc.controller.MvcController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MvcModule {
    @Provides
    @Singleton
    fun providesMvcController(remoteDataSource: RemoteDataSource): MvcController {
        return MvcController(remoteDataSource)
    }
}