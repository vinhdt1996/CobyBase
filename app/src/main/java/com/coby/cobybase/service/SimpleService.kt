package com.coby.cobybase.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class SimpleService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "SimpleService started", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "SimpleService destroyed", Toast.LENGTH_SHORT).show()
    }

}