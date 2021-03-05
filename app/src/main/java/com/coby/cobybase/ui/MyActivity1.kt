package com.coby.cobybase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.R
import kotlinx.android.synthetic.main.activity_my1.*

class MyActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my1)

        btnGoTo2.setOnClickListener {
            startActivity(Intent(this, MyActivity2::class.java))
        }
    }
}