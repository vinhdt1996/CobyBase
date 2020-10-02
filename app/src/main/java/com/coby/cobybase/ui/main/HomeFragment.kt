package com.coby.cobybase.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
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
import com.coby.cobybase.viewmodel.HomeViewModel
import com.coby.cobybase.vo.PopUp
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    private val viewModel: HomeViewModel by viewModel()

    private val feedPagingAdapter = FeedPagingAdapter()

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "Home"
        toolbar?.findViewById<AppCompatImageButton>(R.id.btnBack)?.gone()
    }

    override fun initObservers() {
        viewModel.loadStateLiveData.observe(this, Observer {

        })

        viewModel.feedsLiveData.observe(this, Observer {
            Log.d("vinnne", "$it")
            feedPagingAdapter.submitList(it)
        })

        viewModel.getFeeds()
    }

    override fun initViews() {
        binding.rcvFeed.adapter = feedPagingAdapter
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