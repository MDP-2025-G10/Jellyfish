package com.example.mdp.notifications

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission

class BootReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Create notification channel if it doesn't exist
            NotificationHelper.createNotificationChannel(context)
            // Send the notification
            NotificationHelper.sendNotification(
                context,
                "Welcome Back!",
                "Your app is ready to use!",
                notificationid = 2

            )
        }
    }
}