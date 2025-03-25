package com.example.mdp.ui.components.home

import android.content.pm.PackageManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import android.Manifest
import android.util.Log
import com.example.mdp.notifications.NotificationHelper

@Composable
fun CaloriesBar(amountsConsumed: Float, dailyAmountGoal: Float) {
    val progress = amountsConsumed / dailyAmountGoal


    //work in progress
    val currentAmountsConsumed = rememberUpdatedState(amountsConsumed)

    //work in progress
    if (currentAmountsConsumed.value != amountsConsumed) {
            CaloriesNotification(amountsConsumed)

    }




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
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
        CustomProgressBar(progress)
    }
}

@Composable
fun CustomProgressBar(progress: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        val barWidth = size.width * progress
        drawRoundRect(
            color = Color.LightGray,
            size = androidx.compose.ui.geometry.Size(barWidth, size.height),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
        )
    }
}
//work in progress
//to  complete later
@Composable
fun CaloriesNotification(amountsConsumed: Float) {
    val context = LocalContext.current

    Log.e("CaloriesNotification", "Amounts consumed: $amountsConsumed")

    LaunchedEffect(amountsConsumed) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {

                NotificationHelper.sendNotification(
                    context = context,
                    title = "Calories Updated",
                    message = "You have consumed ${amountsConsumed.toInt()} calories today.",
                    notificationid = 3
                )
            }
        }
    }

