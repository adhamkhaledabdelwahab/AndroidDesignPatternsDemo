package com.example.designpatternsdemo.core.api_service

import com.example.designpatternsdemo.core.model.ProductModel
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductModel>
}