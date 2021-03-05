package com.coby.cobybase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.R
import kotlinx.android.synthetic.main.activity_my1.*
import kotlinx.android.synthetic.main.activity_my4.*

class MyActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my4)

        btn1Again.setOnClickListener {
            startActivity(Intent(this, MyActivity1::class.java))
        }
        btn2Again.setOnClickListener {
            startActivity(Intent(this, MyActivity2::class.java))
        }
        btn3Again.setOnClickListener {
            startActivity(Intent(this, MyActivity3::class.java))
        }
        btn4Again.setOnClickListener {
            startActivity(Intent(this, MyActivity4::class.java))
        }
    }
}