package com.coby.cobybase.ui.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.databinding.ActivityAuthBinding


const val SELECT_PHOTO = 1

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            builtInZoomControls = true
            allowFileAccess = true
        }
        binding.webView.addJavascriptInterface(JavascriptBridge(this), "Android")
        binding.webView.loadUrl("file:///android_asset/webdemo.html");

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SELECT_PHOTO -> {
                if (resultCode == RESULT_OK) {
                    val selectedImage = intent.data
                    binding.webView.loadUrl(
                        "javascript:setFileUri('" + selectedImage.toString().toString() + "')"
                    )
                    val path = getRealPathFromURI(this, selectedImage)
                    binding.webView.loadUrl("javascript:setFilePath('$path')")
                }
            }
        }
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor =
                context.contentResolver.query(contentUri ?: return null, proj, null, null, null)
            val columnIndex =
                cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: return null
            cursor.moveToFirst()
            cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }

    inner class JavascriptBridge(private val activity: Activity) {
        @JavascriptInterface
        fun showToast(s: String) {
            Toast.makeText(this@AuthActivity, s, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun choosePhoto(): String {
            val file = "test"
            val photoPickerIntent = Intent(ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(photoPickerIntent, SELECT_PHOTO)
            return file
        }
    }

}

