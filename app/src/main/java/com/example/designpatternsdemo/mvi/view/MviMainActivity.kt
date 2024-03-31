package com.example.designpatternsdemo.mvi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpatternsdemo.R
import com.example.designpatternsdemo.core.adapter.ProductListAdapter
import com.example.designpatternsdemo.databinding.ActivityMviMainBinding
import com.example.designpatternsdemo.databinding.ActivityMvvmMainBinding
import com.example.designpatternsdemo.mvi.intent.MviIntent
import com.example.designpatternsdemo.mvi.intent.MviState
import com.example.designpatternsdemo.mvi.intent.MviViewModel
import com.example.designpatternsdemo.mvvm.view_model.MvvmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MviMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMviMainBinding

    private lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var mviViewModel: MviViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMviMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initActivityViews()
        observeViewModelState()
        mviViewModel.addIntent(MviIntent.FetchProducts)
    }

    private fun initActivityViews() {
        binding.actionBar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.actionBar.setNavigationOnClickListener {
            finish()
        }
        productListAdapter =
            ProductListAdapter(
                this@MviMainActivity,
                ArrayList()
            )
        binding.productList.layoutManager = LinearLayoutManager(this@MviMainActivity)
        binding.productList.adapter = productListAdapter
    }

    private fun observeViewModelState() {
        lifecycleScope.launch {
            mviViewModel.state.collect {
                when (it) {
                    is MviState.Success -> {
                        binding.progressIndicator.visibility = View.GONE
                        productListAdapter.updateProducts(it.products)
                    }

                    is MviState.Error -> {
                        binding.progressIndicator.visibility = View.GONE
                    }

                    else -> {

                    }
                }
            }
        }
    }
}