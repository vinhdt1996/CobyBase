package com.coby.cobybase.ui.authentication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.coby.cobybase.MainApplication
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityAuthBinding
import org.json.JSONObject
import java.io.*

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED -> {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
                }
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED -> {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 456)
                }
                else -> {
                    createPDF()
                }
            }
        } else {
            createPDF()
        }
    }

    private fun createPDF() {
//        val document = PdfDocument()
//        val pageInfo = PdfDocument.PageInfo.Builder(
//                containerTest.measuredWidth,
//                containerTest.measuredHeight,
//                1
//        ).create()
//        val page = document.startPage(pageInfo)
//        containerTest.draw(page.canvas)
//        document.finishPage(page)
//        val path =
//                Environment.getExternalStorageDirectory().path + "/vinnPDF.pdf"
//        val file = File(Environment.getExternalStorageDirectory().path,"sf/vinnne.pdf")

        val testFile = File(Environment.getExternalStorageDirectory(), "sf/test.json")

        if (testFile.exists()) {
            Toast.makeText(this, "exists", Toast.LENGTH_SHORT).show()
            val jsonObj = JSONObject(readFromFile())
            Log.d("vinnne", "$jsonObj")
            MainApplication.instance.globalObject = jsonObj

//            Log.d("vinnne", jsonObj.getString("key1"))
        } else {
            Toast.makeText(this, "not exists", Toast.LENGTH_SHORT).show()
        }

//        val file  = File(Environment.getExternalStorageDirectory(), "sf")
//        if (!file.exists()){
//            if (!file.mkdirs()){
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show()
//                val file2 = File(Environment.getExternalStorageDirectory().path + "/sf/vinnne.pdf")
////                document.writeTo(FileOutputStream(file2))
////                document.close()
//            }
//        } else {
//            Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show()
//            val file2 = File(Environment.getExternalStorageDirectory().path + "/sf/vinnne.pdf")
////            document.writeTo(FileOutputStream(file2))
////            document.close()
//        }

    }

    private fun readFromFile(): String {
        var ret = ""
        var inputStream: FileInputStream? = FileInputStream(
            File(
                Environment.getExternalStorageDirectory(),
                "sf/test.json"
            )
        )
        try {
//            inputStream = openFileInput("sf/test.json")
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append(receiveString)
                }
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: " + e.toString())
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return ret
    }

    private fun createFolder() {
        val vinnne = "vinnne/data"
        val vinnneFolder = File(filesDir, vinnne)
        vinnneFolder.mkdirs()

//        val publicC = "vinnne/public"

    }
}