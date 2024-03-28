package com.example.designpatternsdemo.mvc.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpatternsdemo.R
import com.example.designpatternsdemo.databinding.ActivityMvcMainBinding
import com.example.designpatternsdemo.mvc.controller.MvcController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MvcMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMvcMainBinding

    private lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var mvcController: MvcController

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMvcMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionBar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.actionBar.setNavigationOnClickListener {
            finish()
        }
        productListAdapter =
            ProductListAdapter(
                this@MvcMainActivity,
                ArrayList()
            )
        binding.productList.layoutManager = LinearLayoutManager(this@MvcMainActivity)
        binding.productList.adapter = productListAdapter
        lifecycleScope.launch {
            val productList = mvcController.getProducts()
            binding.progressIndicator.visibility = View.GONE
            productList?.let {
                productListAdapter.updateProducts(it)
            }
        }
    }
}