package com.example.mdp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import com.example.mdp.notifications.NotificationHelper
import org.koin.androidx.compose.koinViewModel


@Composable
fun Setting(navController: NavController, authViewModel: AuthViewModel = koinViewModel()) {
    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                authViewModel
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text("Setting Screen")

        //testing notification button
        // to be removed
            Button(
                onClick = {
                    if (androidx.core.content.ContextCompat.checkSelfPermission(
                            navController.context,
                            android.Manifest.permission.POST_NOTIFICATIONS
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.d("SettingScreen", "Permission granted, sending notification")
                        NotificationHelper.sendNotification(
                            context = navController.context,
                            title = "Test Notification",
                            message = "This is a test notification from the setting screen!",
                            notificationid = 1

                        )
                    } else {
                        Log.d("SettingScreen", "Permission not granted")
                        // Handle the case where the permission is not granted
                    }
                }
            ) {
                // Button Text
                Text(text = "Send Test Notification")



            }
        }
    }
}