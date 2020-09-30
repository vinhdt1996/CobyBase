package com.coby.cobybase.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_auth
}