package com.coby.cobybase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding: ViewDataBinding>: AppCompatActivity() {

    lateinit var binding: Binding

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initViews()
        initObservers()
    }

    open fun initViews(){}

    open fun initObservers(){}

}