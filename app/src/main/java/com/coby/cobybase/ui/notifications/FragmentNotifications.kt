package com.coby.cobybase.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.databinding.FragmentNotificationsBinding
import com.coby.cobybase.ext.gone
import com.coby.cobybase.utils.sendNotification

class FragmentNotifications : BaseFragment<FragmentNotificationsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notifications

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "Notifications"
        toolbar?.findViewById<AppCompatImageButton>(R.id.btnBack)?.gone()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createChannel(
            getString(R.string.egg_notification_channel_id),
            "Channel Name"
        )
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Time for breakfast"
            }
            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun initEventListeners() {
        binding.btnShowNoti.setOnClickListener {
            val notificationManager = ContextCompat.getSystemService(
                requireContext(),
                NotificationManager::class.java
            )
            notificationManager?.sendNotification(
                "My Body",
                requireContext()
            )
        }
    }
}