package com.example.designpatternsdemo.mvp.view

import com.example.designpatternsdemo.core.model.ProductModel

interface MvpView {
    fun showProducts(products: List<ProductModel>)
}