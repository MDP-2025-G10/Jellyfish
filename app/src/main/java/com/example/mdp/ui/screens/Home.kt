package com.example.mdp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.ui.components.home.CalorieHistoryChart
import com.example.mdp.ui.components.home.DailyIntakeProgressCard
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import com.example.mdp.data.viewmodel.MealViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun Home(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel(),
    mealViewModel: MealViewModel = koinViewModel()
) {
    Scaffold(
        topBar = { TopBar(navController, authViewModel) },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DailyIntakeProgressCard(navController, mealViewModel)
            CalorieHistoryChart(mealViewModel)
        }
    }
}
