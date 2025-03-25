package com.example.mdp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workouts(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Auto-increment primary key
    val name: String,  // Workout name
    val description: String,  // Description of the workout
    val reps: Int,  // Number of reps
    val sets: Int,  // Number of sets
    val weight: Int,  // Weight lifted
    val timestamp: Long = System.currentTimeMillis()  // Timestamp of the workout
)