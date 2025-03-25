package com.example.mdp.ui.components.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.navigation.NavRoutes
import com.example.mdp.viewmodels.MealViewModel

@Composable
fun DailyIntakeProgressCard(
    navController: NavController,
    mealViewModel: MealViewModel
) {
    val nutritionInfo by mealViewModel.todayNutrition.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(NavRoutes.RouteToNutrition.route) }
    ) {
        CaloriesBar(nutritionInfo.calories.toFloat(), 2000f)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                CircularProgressCard(
                    amountsConsumed = nutritionInfo.fats.toFloat(),
                    dailyAmountGoal = 100f,
                    size = 100.dp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    type = "Fats"
                )
                CircularProgressCard(
                    amountsConsumed = nutritionInfo.proteins.toFloat(),
                    dailyAmountGoal = 75f,
                    size = 100.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    type = "Carbs"
                )
                CircularProgressCard(
                    amountsConsumed = nutritionInfo.carbs.toFloat(),
                    dailyAmountGoal = 200f,
                    size = 100.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    type = "Protein"
                )
            }
        }
    }
}