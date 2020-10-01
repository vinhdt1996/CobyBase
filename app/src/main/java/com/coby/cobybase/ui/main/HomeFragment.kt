package com.coby.cobybase.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.databinding.FragmentHomeBinding
import com.coby.cobybase.databinding.LayoutBottomSheetBinding
import com.coby.cobybase.databinding.LayoutPopupBinding
import com.coby.cobybase.ext.gone
import com.coby.cobybase.listener.BottomSheetListener
import com.coby.cobybase.listener.PopUpListener
import com.coby.cobybase.utils.AppEvent
import com.coby.cobybase.vo.PopUp
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "Home"
        toolbar?.findViewById<AppCompatImageButton>(R.id.btnBack)?.gone()
    }

    override fun initEventListeners() {
        binding.btnPopup.setOnClickListener {
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

        binding.btnBottomSheet.setOnClickListener {
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
}