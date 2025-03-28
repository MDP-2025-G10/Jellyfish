package com.example.mdp.notifications

import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.Manifest


class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        val context = applicationContext
        val title = inputData.getString("title") ?: "Reminder"
        val message = inputData.getString("message") ?: "This is your scheduled reminder!"

        NotificationHelper.sendNotification(
            context = context,
            title = title,
            message = message,
            notificationid = inputData.getInt("reminderId", 1)
        )

        return Result.success()
    }
}