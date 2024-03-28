package com.example.designpatternsdemo.mvc.controller

import android.util.Log
import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.core.model.ProductModel
import javax.inject.Inject

class MvcController @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getProducts(): List<ProductModel>? {
        return try {
            remoteDataSource.getProducts()
        } catch (e: Exception) {
            Log.e(
                "MVC Controller",
                e.localizedMessage?.toString() ?: "Error Occurred"
            )
            null
        }
    }
}