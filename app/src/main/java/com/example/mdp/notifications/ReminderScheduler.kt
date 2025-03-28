package com.example.mdp.notifications

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.mdp.notifications.NotificationWorker
import java.util.concurrent.TimeUnit

object ReminderScheduler {

    fun scheduleReminder(context: Context, reminderId: Int, intervalMinutes: Long, title: String, message: String) {
        val inputData = Data.Builder()
            .putInt("reminderId", reminderId)
            .putLong("intervalMinutes", intervalMinutes)
            .putString("title", title)
            .putString("message", message)
            .build()

        val reminderRequest: WorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInitialDelay(intervalMinutes, TimeUnit.MINUTES)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueue(reminderRequest)
    }

    fun scheduleAllReminders(context: Context) {
        val sharedPreferences = context.getSharedPreferences("ReminderPrefs", Context.MODE_PRIVATE)

        val waterInterval = sharedPreferences.getLong("water_interval", 30)
        val taskInterval = sharedPreferences.getLong("task_interval", 60)
        val exerciseInterval = sharedPreferences.getLong("exercise_interval", 120)

        scheduleReminder(context, 4, waterInterval, "Water Reminder", "Time to drink water!")
        scheduleReminder(context, 5, taskInterval, "Task Reminder", "Time to complete your task!")
        scheduleReminder(context, 6, exerciseInterval, "Exercise Reminder", "Time to exercise!")
    }
}