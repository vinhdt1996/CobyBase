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
import com.coby.cobybase.utils.PopupUtil
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
            PopupUtil.showPopupDemo()
        }

        binding.btnBottomSheet.setOnClickListener {
            PopupUtil.showBottomSheetDemo()
        }
    }
}