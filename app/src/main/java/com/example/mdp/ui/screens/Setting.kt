package com.example.mdp.ui.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.notifications.ReminderScheduler
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun Setting(navController: NavController) {
    // SharedPreferences
    val sharedPreferences: SharedPreferences = navController.context.getSharedPreferences("ReminderPrefs", Context.MODE_PRIVATE)

    // State for user input fields
    val waterIntervalState = remember { mutableStateOf(TextFieldValue(sharedPreferences.getLong("water_interval", 30).toString())) }
    val taskIntervalState = remember { mutableStateOf(TextFieldValue(sharedPreferences.getLong("task_interval", 60).toString())) }
    val exerciseIntervalState = remember { mutableStateOf(TextFieldValue(sharedPreferences.getLong("exercise_interval", 120).toString())) }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                authViewModel = koinViewModel() // Assuming you use a view model for authentication
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
            Card() {

                // Title
                Text("Settings Screen")

                // Water Reminder Input
                Text("Water Reminder Interval (minutes)")
                BasicTextField(
                    value = waterIntervalState.value,
                    onValueChange = { waterIntervalState.value = it },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                // Task Reminder Input
                Text("Task Reminder Interval (minutes)")
                BasicTextField(
                    value = taskIntervalState.value,
                    onValueChange = { taskIntervalState.value = it },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                // Exercise Reminder Input
                Text("Exercise Reminder Interval (minutes)")
                BasicTextField(
                    value = exerciseIntervalState.value,
                    onValueChange = { exerciseIntervalState.value = it },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                // Save Button
                Button(
                    onClick = {
                        val waterInterval = waterIntervalState.value.text.toLongOrNull() ?: 30
                        val taskInterval = taskIntervalState.value.text.toLongOrNull() ?: 60
                        val exerciseInterval = exerciseIntervalState.value.text.toLongOrNull() ?: 120

                        // Save the intervals to SharedPreferences
                        sharedPreferences.edit().apply {
                            putLong("water_interval", waterInterval)
                            putLong("task_interval", taskInterval)
                            putLong("exercise_interval", exerciseInterval)
                            apply()
                        }

                        // Schedule reminders using ReminderScheduler
                        ReminderScheduler.scheduleReminder(navController.context, 4, waterInterval, "Water Reminder", "Time to drink water!")  // Water Reminder
                        ReminderScheduler.scheduleReminder(navController.context, 5, taskInterval, "Task Reminder", "Time to complete your task!")   // Task Reminder
                        ReminderScheduler.scheduleReminder(navController.context, 6, exerciseInterval, "Exercise Reminder", "Time to exercise!") // Exercise Reminder

                        Toast.makeText(navController.context, "Reminders saved and scheduled!", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("Save Reminders")
                }
            }
        }
    }
}
