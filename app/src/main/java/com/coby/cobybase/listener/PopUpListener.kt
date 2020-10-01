package com.coby.cobybase.listener

class PopUpListener(
    val clickCenterListener: () -> Unit = {}
) {
    fun onClickCenterListener() = clickCenterListener()
}