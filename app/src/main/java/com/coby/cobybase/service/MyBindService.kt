package com.coby.cobybase.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.net.URL

class MyBindService : Service() {

    private val binder = MyBinder()
    var counter: Int = 0
    var urls = arrayOf<URL>()
    val UPDATE_INTERVAL = 1000

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        fun getService(): MyBindService = this@MyBindService
    }
}