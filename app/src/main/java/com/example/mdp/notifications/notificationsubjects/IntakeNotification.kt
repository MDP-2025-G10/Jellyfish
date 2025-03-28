package com.example.mdp.notifications.notificationsubjects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.mdp.data.viewmodel.MealViewModel
import com.example.mdp.notifications.NotificationHelper
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext


@androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS)
@Composable
fun IntakeNotification(mealViewModel: MealViewModel, totalCalories: Int, dailyGoal: Int) {
    val calorieHistory by mealViewModel.calorieHistory.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(calorieHistory) {
        if (calorieHistory.isNotEmpty()) {
            val lastEntry = calorieHistory.last()
            val message = if (totalCalories > dailyGoal) {
                val exceededBy = totalCalories - dailyGoal
                "You have exceeded your daily goal by $exceededBy calories! Total calories: $totalCalories"
            } else {
                "Food with ${lastEntry.totalCalories} calories has been added to your tracked calories."
            }
            NotificationHelper.sendNotification(
                context = context,
                title = "Calorie Tracker",
                message = message,
                notificationid = 3
            )
        }
    }
}