package com.example.designpatternsdemo.mvp.presenter

import android.util.Log
import com.example.designpatternsdemo.core.data_source.RemoteDataSource
import com.example.designpatternsdemo.mvp.view.MvpView
import javax.inject.Inject

class MvpPresenter @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getProducts(mvpView: MvpView) {
        try {
            val products = remoteDataSource.getProducts()
            mvpView.showProducts(products)
        } catch (e: Exception) {
            Log.e(
                "MVP Presenter",
                e.localizedMessage?.toString() ?: "Error Occurred"
            )
        }
    }
}