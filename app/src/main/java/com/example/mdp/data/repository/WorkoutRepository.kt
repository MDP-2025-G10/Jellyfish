package com.example.mdp.data.repository

import android.util.Log
import com.example.mdp.data.database.WorkoutDao

import com.example.mdp.data.model.Workouts

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    // Insert a meal into the database
    suspend fun insertWorkout(workout: Workouts) {
        workoutDao.insertWorkout(workout)
    }

    // Get all meals from the database
    suspend fun getAllWorkouts(): List<Workouts> {
        return workoutDao.getAllWorkouts()
    }

    // Delete a meal from the database
    suspend fun deleteWorkout(workout: Workouts) {
        workoutDao.deleteWorkout(workout)
    }

    // Insert a test meal into the database
    suspend fun insertTestWorkout() {
        Log.d("WorkoutRepository", "Inserting test workout")
        val testWorkout = Workouts(
            name = "Test Workout",
            description = "This is a test workout",
            reps = 10,
            sets = 3,
            weight = 100,
            timestamp = System.currentTimeMillis()
        )
        insertWorkout(testWorkout)
    }
}