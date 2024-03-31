package com.example.designpatternsdemo.mvi.di

import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.mvi.intent.MviViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MviModule {
    @Provides
    fun providesMviViewModel(remoteDataSource: RemoteDataSource): MviViewModel {
        return MviViewModel(remoteDataSource)
    }
}