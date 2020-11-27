package com.coby.cobybase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TestModel(
    var id: String = "",
    var title: String = ""
) : Parcelable