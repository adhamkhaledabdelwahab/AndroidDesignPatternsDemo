package com.example.designpatternsdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.designpatternsdemo.databinding.ActivityMainBinding
import com.example.designpatternsdemo.mvc.view.MvcMainActivity

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mvc.setOnClickListener(this)
        binding.mvp.setOnClickListener(this)
        binding.mvvm.setOnClickListener(this)
        binding.mvi.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.mvc.id -> gotoNewActivity(MvcMainActivity::class.java)
//            binding.mvp.id -> gotoNewActivity(MvpMainActivity::class.java)
//            binding.mvvm.id -> gotoNewActivity(MvvmMainActivity::class.java)
//            binding.mvi.id -> gotoNewActivity(MviMainActivity::class.java)
        }
    }

    private fun gotoNewActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}