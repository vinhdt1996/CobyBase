package com.coby.cobybase.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bnvMain, navHostFragment.navController)
    }
}