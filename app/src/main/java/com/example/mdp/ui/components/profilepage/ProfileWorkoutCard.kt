package com.example.mdp.ui.components.profilepage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mdp.data.model.Workouts

@Composable
fun ProfilePageWorkoutCard(workouts: Workouts) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = workouts.name,
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = workouts.description,
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleMedium
        )
    }
}