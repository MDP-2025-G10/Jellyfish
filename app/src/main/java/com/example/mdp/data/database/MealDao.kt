package com.example.mdp.data.database


import androidx.room.*
import com.example.mdp.data.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("SELECT * FROM meals ORDER BY timestamp DESC")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE DATE(timestamp / 1000, 'unixepoch') = DATE('now')")
    fun getTodayMeals(): Flow<List<Meal>>

    @Delete
    suspend fun deleteMeal(meal: Meal)
}