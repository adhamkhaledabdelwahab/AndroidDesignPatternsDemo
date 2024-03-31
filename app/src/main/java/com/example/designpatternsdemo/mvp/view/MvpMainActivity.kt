package com.example.designpatternsdemo.mvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpatternsdemo.R
import com.example.designpatternsdemo.core.adapter.ProductListAdapter
import com.example.designpatternsdemo.core.model.ProductModel
import com.example.designpatternsdemo.databinding.ActivityMvpMainBinding
import com.example.designpatternsdemo.mvp.presenter.MvpPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MvpMainActivity : AppCompatActivity(), MvpView {
    private lateinit var binding: ActivityMvpMainBinding

    private lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var mvpPresenter: MvpPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMvpMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActivityViews()
        lifecycleScope.launch {
            mvpPresenter.getProducts(this@MvpMainActivity)
        }
    }

    private fun initActivityViews() {
        binding.actionBar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.actionBar.setNavigationOnClickListener {
            finish()
        }
        productListAdapter =
            ProductListAdapter(
                this@MvpMainActivity,
                ArrayList()
            )
        binding.productList.layoutManager = LinearLayoutManager(this@MvpMainActivity)
        binding.productList.adapter = productListAdapter
    }

    override fun showProducts(products: List<ProductModel>) {
        binding.progressIndicator.visibility = View.GONE
        productListAdapter.updateProducts(products)
    }
}