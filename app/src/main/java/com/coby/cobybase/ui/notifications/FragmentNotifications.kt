package com.coby.cobybase.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.databinding.FragmentNotificationsBinding
import com.coby.cobybase.ext.gone

class FragmentNotifications: BaseFragment<FragmentNotificationsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notifications

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "Notifications"
        toolbar?.findViewById<AppCompatImageButton>(R.id.btnBack)?.gone()
    }
}