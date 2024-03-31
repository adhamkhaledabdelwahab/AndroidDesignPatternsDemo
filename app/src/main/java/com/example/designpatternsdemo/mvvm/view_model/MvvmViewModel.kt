package com.example.designpatternsdemo.mvvm.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.core.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class MvvmViewModel @Inject constructor(private var remoteDataSource: RemoteDataSource) :
    ViewModel() {
    private val _productsList: MutableLiveData<List<ProductModel>> = MutableLiveData()
    val productsList: MutableLiveData<List<ProductModel>>
        get() = _productsList

    fun getProducts() {
        viewModelScope.launch {
            try {
                productsList.value = remoteDataSource.getProducts()
            } catch (e: Exception) {
                Log.e(
                    "MVP Presenter",
                    e.localizedMessage?.toString() ?: "Error Occurred"
                )
            }
        }
    }
}