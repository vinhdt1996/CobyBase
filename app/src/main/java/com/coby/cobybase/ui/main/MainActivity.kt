package com.coby.cobybase.ui.main

import android.content.Intent
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.coby.cobybase.MainApplication
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityMainBinding
import com.coby.cobybase.ui.authentication.AuthActivity
import com.coby.cobybase.utils.PopupUtil

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bnvMain, navHostFragment.navController)
    }

    override fun initEventListeners() {
        binding.bnvMain.menu.findItem(R.id.menuLogout).setOnMenuItemClickListener {
            PopupUtil.showPopupLogout() {
                (application as MainApplication).clearPref()
                startActivity(Intent(this, AuthActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
                finish()
            }
            true
        }
    }
}