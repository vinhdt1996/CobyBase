package com.coby.cobybase

import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("keyText")
fun setKeyText(tv: TextView, keyText: String) {
    tv.text = MainApplication.instance.globalObject?.getString(keyText)
}