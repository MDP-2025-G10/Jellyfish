package com.example.mdp.ui.components.home

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mdp.data.viewmodel.MealViewModel
import com.example.mdp.notifications.notificationsubjects.IntakeNotification

@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
@Composable
fun CaloriesBar(mealViewModel: MealViewModel, amountsConsumed: Float, dailyAmountGoal: Float) {
    IntakeNotification(mealViewModel = mealViewModel, totalCalories = amountsConsumed.toInt(), dailyGoal = dailyAmountGoal.toInt())
    val progress = amountsConsumed / dailyAmountGoal
    val isOverLimit = amountsConsumed > dailyAmountGoal
    val textColor = if (isOverLimit) Color(0xFFAA5559) else Color.LightGray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Calories",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "${amountsConsumed.toInt()}/${dailyAmountGoal.toInt()}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp),
                color = textColor
            )
        }
        CustomProgressBar(progress, textColor)
    }
}

@Composable
fun CustomProgressBar(progress: Float, progressColor: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        val barWidth = size.width
        val clampedProgress = progress.coerceIn(0f, 1f) // Prevent overflow
        val progressWidth = barWidth * clampedProgress
        val barHeight = size.height
        val cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())

        // background track
        drawRoundRect(
            color = Color(0xFF5E5E5E),
            size = Size(barWidth, barHeight),
            cornerRadius = cornerRadius
        )

        // foreground progress
        drawRoundRect(
            color = progressColor,
            size = Size(progressWidth, barHeight),
            cornerRadius = cornerRadius
        )
    }
}


