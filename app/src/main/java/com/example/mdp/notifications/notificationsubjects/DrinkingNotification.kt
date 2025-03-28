package com.example.mdp.notifications.notificationsubjects

//work in progress
//water reminder notifies user to drink water
// based on the amount of time that the user has input
// the user input is the amount of time between each reminder
//the reminder  should be  sent out during waking hours or relavent hours

//look at using work manager to send out notifications at the desired intervals
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.example.mdp.notifications.NotificationWorker
import java.util.concurrent.TimeUnit

fun scheduleWaterReminder(context: Context, intervalMinutes: Long, title: String, message: String) {
    val workRequest: WorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(intervalMinutes, TimeUnit.MINUTES)
        .setInputData(
            workDataOf(
                "title" to title,
                "message" to message,
                "reminderId" to 4
            )
        )
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}

