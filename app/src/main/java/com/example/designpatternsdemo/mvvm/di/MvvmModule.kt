package com.example.designpatternsdemo.mvvm.di

import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.mvvm.view_model.MvvmViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MvvmModule {
    @Provides
    fun providesMvvmViewModel(remoteDataSource: RemoteDataSource): MvvmViewModel {
        return MvvmViewModel(remoteDataSource)
    }
}