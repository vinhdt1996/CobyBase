package com.coby.cobybase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.R
import kotlinx.android.synthetic.main.activity_my1.*
import kotlinx.android.synthetic.main.activity_my1.btnGoTo2
import kotlinx.android.synthetic.main.activity_my2.*

class MyActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my2)

        btnGoTo3.setOnClickListener {
            startActivity(Intent(this, MyActivity3::class.java))
        }
    }
}