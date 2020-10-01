package com.coby.cobybase.utils

import com.coby.cobybase.vo.PopUp
import java.util.concurrent.CopyOnWriteArraySet

object AppEvent {

    private var popupEventListeners: Set<PopupEventListener> = CopyOnWriteArraySet()

    fun registerPopupEventListener(listener: PopupEventListener?){
        listener?.let {it
            popupEventListeners = popupEventListeners.plus(listener)
        }
    }

    fun unregisterPopupEventListener(listener: PopupEventListener?){
        listener?.let {it
            popupEventListeners = popupEventListeners.minus(listener)
        }
    }

    fun notifyShowPopUp(popup: PopUp? = null) { // default show loading
        for (listener in popupEventListeners)
            listener.onShowPopup(popup)
    }

    fun notifyClosePopUp() {
        for (listener in popupEventListeners)
            listener.onClosePopup()
    }

}

interface PopupEventListener{
    fun onShowPopup(popup: PopUp?)
    fun onClosePopup()
}