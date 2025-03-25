package com.example.mdp.data.database

import androidx.room.*
import com.example.mdp.data.model.Workouts

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workouts: Workouts)

    @Query("SELECT * FROM workouts ORDER BY timestamp DESC")
    suspend fun getAllWorkouts(): List<Workouts>

    @Delete
    suspend fun deleteWorkout(workouts: Workouts)
}