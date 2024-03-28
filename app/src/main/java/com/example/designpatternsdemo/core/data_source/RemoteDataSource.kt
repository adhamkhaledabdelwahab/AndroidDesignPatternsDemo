package com.example.designpatternsdemo.core.data_source

import com.example.designpatternsdemo.core.model.ProductModel

interface RemoteDataSource {
    suspend fun getProducts(): List<ProductModel>
}