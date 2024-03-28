package com.example.designpatternsdemo.core.model

data class ProductModel(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: ProductRatingModel,
)