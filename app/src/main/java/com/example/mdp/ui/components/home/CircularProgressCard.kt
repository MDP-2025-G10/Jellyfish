package com.example.mdp.ui.components.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressCard(
    amountsConsumed: Float,
    dailyAmountGoal: Float,
    size: Dp,
    color: Color,
    type: String
) {
    val progress = amountsConsumed / dailyAmountGoal

    // Circular Progress Indicator
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(size),
            color = color,
            strokeWidth = 6.dp,
            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
        )
        Column {
            Text(type)
            Text("$amountsConsumed g")
        }

//        Text("$progress")
    }
}


