package com.coby.cobybase.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import java.net.MalformedURLException
import java.net.URL

class BlockingService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "BlockingService started", Toast.LENGTH_SHORT).show()
        try {
            val result = DonwloadFile(URL("http://www.amazon.com/somefile.pdf"))
            Toast.makeText(this, "Downloaded $result bytes", Toast.LENGTH_SHORT).show()
        } catch (e: MalformedURLException){
            e.printStackTrace()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "BlockingService destroyed", Toast.LENGTH_SHORT).show()
    }

    private fun DonwloadFile(url: URL): Int{
        try {
            Thread.sleep(8000)
        } catch (e: InterruptedException){
            e.printStackTrace()
        }
        return 100
    }
}