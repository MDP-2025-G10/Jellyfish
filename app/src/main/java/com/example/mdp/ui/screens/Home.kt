package com.example.mdp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.navigation.LocalAuthViewModel
import com.example.mdp.navigation.LocalUserViewModel
import com.example.mdp.ui.components.home.CalorieHistoryChart
import com.example.mdp.ui.components.home.DailyIntakeProgressCard
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar


@Composable
fun Home(navController: NavController) {


    val authViewModel = LocalAuthViewModel.current
    val currentUser = authViewModel.currentUser.observeAsState().value

    val userViewModel = LocalUserViewModel.current

    LaunchedEffect(currentUser?.uid) {
        currentUser?.uid?.let { uid ->
            Log.d("Profile", "Loading user with UID: $uid")
            userViewModel.loadUser(uid)
        } ?: Log.e("Profile", "Current user UID is null")
    }

    Scaffold(
        topBar = { TopBar(navController) },
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
            DailyIntakeProgressCard(navController)
            CalorieHistoryChart()
        }
    }
}
