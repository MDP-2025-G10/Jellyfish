package com.example.mdp.data.repository

import com.example.mdp.data.database.MealDao
import com.example.mdp.data.model.Meal
import com.example.mdp.data.model.NutritionInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealRepository(private val mealDao: MealDao) {

    val allMeals: Flow<List<Meal>> = mealDao.getAllMeals()
    private val todayMeals: Flow<List<Meal>> = mealDao.getTodayMeals()

    fun getTodayNutrition(): Flow<NutritionInfo> {
        return todayMeals.map { meals ->
            NutritionInfo(
                calories = meals.sumOf { it.calories },
                fats = meals.sumOf { it.fats },
                carbs = meals.sumOf { it.carbs },
                proteins = meals.sumOf { it.proteins }
            )
        }
    }

    // Insert a meal into the database
    suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal)
    }

    // Delete a meal from the database
    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal)
    }

}