package com.coby.cobybase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.coby.cobybase.utils.AppEvent
import com.coby.cobybase.utils.PopupEventListener
import com.coby.cobybase.vo.PopUp
import com.coby.cobybase.widget.BottomPopupDialog
import com.coby.cobybase.widget.PopupDialog
import java.util.concurrent.CopyOnWriteArraySet

abstract class BaseActivity<Binding: ViewDataBinding>: AppCompatActivity(), PopupEventListener {

    lateinit var binding: Binding

    abstract val layoutId: Int

    private var listPopupDialogFragment: Set<DialogFragment> = CopyOnWriteArraySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppEvent.registerPopupEventListener(this)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initViews()
        initEventListeners()
        initObservers()
    }

    override fun onShowPopup(popup: PopUp?) {
        onClosePopup()
        val popupDialogFragment = if (popup?.isBottomSheet == true)
            BottomPopupDialog.newInstance(popup) else PopupDialog.newInstance(popup)
        popupDialogFragment.show(supportFragmentManager, PopupDialog().tag)
        listPopupDialogFragment = listPopupDialogFragment.plus(popupDialogFragment)
    }

    override fun onClosePopup() {
        for (item in listPopupDialogFragment) {
            item.dismissAllowingStateLoss()
            listPopupDialogFragment = listPopupDialogFragment.minus(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppEvent.unregisterPopupEventListener(this)
    }

    open fun initViews(){}

    open fun initEventListeners(){}

    open fun initObservers(){}

}