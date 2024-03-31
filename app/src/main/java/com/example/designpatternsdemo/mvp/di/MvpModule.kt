package com.example.designpatternsdemo.mvp.di

import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.mvp.presenter.MvpPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MvpModule {
    @Provides
    fun providesMvpPresenter(remoteDataSource: RemoteDataSource): MvpPresenter {
        return MvpPresenter(remoteDataSource)
    }
}