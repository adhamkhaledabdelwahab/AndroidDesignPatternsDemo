package com.example.designpatternsdemo.di

import com.example.designpatternsdemo.core.api_service.ApiClient
import com.example.designpatternsdemo.core.api_service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return ApiClient.apiService
    }
}