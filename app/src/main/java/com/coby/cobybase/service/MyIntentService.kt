package com.coby.cobybase.service

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import java.net.MalformedURLException
import java.net.URL

class MyIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Log.d("vinnne","onHandleWork")
        try {
            val result = DownloadFile(URL("http://www.amazon.com/somefile.pdf"))
            Log.d("vinnne","Downloaded $result bytes")
        } catch (e: MalformedURLException){
            e.printStackTrace()
        }
    }

    private fun DownloadFile(url: URL): Int {
        try {
            Thread.sleep(5000);
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return 100
    }

}