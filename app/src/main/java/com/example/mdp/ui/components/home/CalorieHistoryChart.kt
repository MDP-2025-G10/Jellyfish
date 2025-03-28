package com.example.mdp.ui.components.home

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.mdp.data.viewmodel.MealViewModel
import com.example.mdp.notifications.notificationsubjects.IntakeNotification
import com.example.mdp.utils.formatDate
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries


@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
@Composable
fun CalorieHistoryChart(mealViewModel: MealViewModel) {

    val modelProducer = remember { CartesianChartModelProducer() }
    val calorieHistory by mealViewModel.calorieHistory.collectAsState()

    Log.d("calorieHistory", "calorieHistory: $calorieHistory")

    LaunchedEffect(calorieHistory) {
        if (calorieHistory.isNotEmpty()) {
            val xValues = (1..<calorieHistory.size).map { it }
            val yValues = calorieHistory.map { it.totalCalories }
            Log.d("chart", "$xValues")
            Log.d("chart", "$yValues")
            modelProducer.runTransaction {
                lineSeries { series(xValues, yValues) }
            }
        }
    }
//
//    Card {
//        CartesianChartHost(
//            rememberCartesianChart(
//                rememberLineCartesianLayer(),
//                startAxis = VerticalAxis.rememberStart(),
//                bottomAxis = HorizontalAxis.rememberBottom(
//                    valueFormatter = { _, value, _ ->
//                        val index = value.toInt() - 1
//                        if (index in calorieHistory.indices) {
//                            val dateStr = calorieHistory[index].date
//                            formatDate(dateStr)
//                        } else {
//                            ""
//                        }
//                    }
//                ),
//            ),
//            modelProducer,
//        )
//    }
}

