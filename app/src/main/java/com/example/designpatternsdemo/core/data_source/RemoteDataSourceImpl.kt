package com.example.designpatternsdemo.core.data_source

import com.example.designpatternsdemo.core.api_service.ApiService
import com.example.designpatternsdemo.core.model.ProductModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private var apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getProducts(): List<ProductModel> =
        runCatching { apiService.getProducts() }.getOrElse { throw it }
}