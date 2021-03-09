package com.coby.cobybase.service

import android.app.job.JobScheduler
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coby.cobybase.R
import kotlinx.android.synthetic.main.activity_my_service.*

class MyServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_service)

        btnStartService.setOnClickListener {
//            startService(Intent(this, SimpleService::class.java))
//            startService(Intent(this, BlockingService::class.java))


        }

        btnStopService.setOnClickListener {
//            stopService(Intent(this, SimpleService::class.java))
//            stopService(Intent(this, BlockingService::class.java))
        }
    }
}