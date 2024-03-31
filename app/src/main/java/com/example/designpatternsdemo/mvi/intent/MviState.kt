package com.example.designpatternsdemo.mvi.intent

import com.example.designpatternsdemo.core.model.ProductModel

sealed class MviState {
    data object Idle : MviState()
    data object Loading : MviState()
    data class Success(val products: List<ProductModel>) : MviState()
    data class Error(val error: String?) : MviState()
}