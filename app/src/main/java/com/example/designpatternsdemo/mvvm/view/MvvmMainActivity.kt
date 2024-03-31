package com.example.designpatternsdemo.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpatternsdemo.R
import com.example.designpatternsdemo.core.adapter.ProductListAdapter
import com.example.designpatternsdemo.databinding.ActivityMvpMainBinding
import com.example.designpatternsdemo.databinding.ActivityMvvmMainBinding
import com.example.designpatternsdemo.mvvm.view_model.MvvmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MvvmMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMvvmMainBinding

    private lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var mvvmViewModel: MvvmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMvvmMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActivityViews()
        mvvmViewModel.productsList.observe(
            this@MvvmMainActivity,
        ) {
            binding.progressIndicator.visibility = View.GONE
            productListAdapter.updateProducts(it)
        }
        mvvmViewModel.getProducts()
    }

    private fun initActivityViews() {
        binding.actionBar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.actionBar.setNavigationOnClickListener {
            finish()
        }
        productListAdapter =
            ProductListAdapter(
                this@MvvmMainActivity,
                ArrayList()
            )
        binding.productList.layoutManager = LinearLayoutManager(this@MvvmMainActivity)
        binding.productList.adapter = productListAdapter
    }
}