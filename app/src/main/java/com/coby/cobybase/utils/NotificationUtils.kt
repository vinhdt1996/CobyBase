package com.coby.cobybase.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.coby.cobybase.R
import com.coby.cobybase.ui.main.MainActivity

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context){

    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        0,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle("My Title")
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
    notify(0, builder.build())
}