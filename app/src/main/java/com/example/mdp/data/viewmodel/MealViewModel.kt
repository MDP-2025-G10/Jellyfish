package com.example.mdp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdp.data.model.DailyCalories
import com.example.mdp.data.model.Meal
import com.example.mdp.data.model.NutritionInfo
import com.example.mdp.data.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MealViewModel(private val mealRepository: MealRepository) : ViewModel() {

    val todayNutrition: StateFlow<NutritionInfo> = mealRepository.getTodayNutrition()
        .stateIn(viewModelScope, SharingStarted.Lazily, NutritionInfo())

    val allMealList: StateFlow<List<Meal>> = mealRepository.allMeals
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val calorieHistory: StateFlow<List<DailyCalories>> =
        mealRepository.getCaloriesForLast7Days()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertMeal(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        mealRepository.insertMeal(meal)
    }

    fun deleteMeal(meal: Meal) = viewModelScope.launch(Dispatchers.IO) {
        mealRepository.deleteMeal(meal)
    }
}