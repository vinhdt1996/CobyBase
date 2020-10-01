package com.coby.cobybase.utils

import androidx.databinding.ViewDataBinding
import com.coby.cobybase.R
import com.coby.cobybase.databinding.LayoutBottomSheetBinding
import com.coby.cobybase.databinding.LayoutPopupBinding
import com.coby.cobybase.listener.BottomSheetListener
import com.coby.cobybase.listener.PopUpListener
import com.coby.cobybase.vo.PopUp

object PopupUtil {

    fun showLoading() {
        AppEvent.notifyShowPopUp()
    }

    fun hideAllPopup() {
        AppEvent.notifyClosePopUp()
    }

    fun showPopupLogout(onConfirm: () -> Unit) {
        AppEvent.notifyShowPopUp(
            PopUp(
                R.layout.layout_popup,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutPopupBinding)?.apply {
                        title = "Warning"
                        message = "Are you sure you want to logout?"
                        left = "Cancel"
                        right = "Confirm"
                        clickListener = PopUpListener(
                            clickLeftListener = { AppEvent.notifyClosePopUp() },
                            clickRightListener = onConfirm
                        )
                    }
                }
            )
        )
    }

    fun showPopupError(msg: String) {
        AppEvent.notifyShowPopUp(
            PopUp(
                R.layout.layout_popup,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutPopupBinding)?.apply {
                        title = "Error"
                        message = msg
                        center = "Close"
                        clickListener = PopUpListener(
                            clickCenterListener = { AppEvent.notifyClosePopUp() }
                        )
                    }
                }
            )
        )
    }

    fun showPopupDemo() {
        AppEvent.notifyShowPopUp(
            PopUp(
                popupId = R.layout.layout_popup,
                isCancelable = true,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutPopupBinding)?.apply {
                        title = "Error"
                        message = "Message"
                        center = "Close"
                        clickListener = PopUpListener(
                            clickCenterListener = { AppEvent.notifyClosePopUp() }
                        )
                    }
                }
            )
        )
    }

    fun showBottomSheetDemo() {
        AppEvent.notifyShowPopUp(
            PopUp(
                popupId = R.layout.layout_bottom_sheet,
                isBottomSheet = true,
                isCancelable = true,
                callback = { binding: ViewDataBinding?, _, _ ->
                    (binding as? LayoutBottomSheetBinding)?.apply {
                        title = "Title"
                        action1 = "Action 1"
                        action2 = "Action 2"
                        action3 = "Action 3"
                        action4 = "Action 4"
                        hasArrow = true
                        clickListener = BottomSheetListener(
                            clickAction1Listener = { },
                            clickAction2Listener = { },
                            clickAction3Listener = { },
                            clickAction4Listener = { },
                            clickDismissListener = { AppEvent.notifyClosePopUp() }
                        )
                    }
                }
            )
        )
    }

}