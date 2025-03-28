package com.example.mdp.utils

import com.example.mdp.data.model.Meal
import com.example.mdp.usda.model.FoodItem

object FoodMapper {
    fun convertFoodItemToMeal(foodItem: FoodItem): Meal {
        val calories =
            foodItem.nutrients.find { it.name.contains("Energy", true) }?.value?.toInt() ?: 0
        val carbs =
            foodItem.nutrients.find { it.name.contains("Carbohydrate", true) }?.value?.toInt() ?: 0
        val protein =
            foodItem.nutrients.find { it.name.contains("Protein", true) }?.value?.toInt() ?: 0
        val fats = foodItem.nutrients.find { it.name.contains("Fat", true) }?.value?.toInt() ?: 0

        return Meal(
            name = foodItem.name,
            calories = calories,
            carbs = carbs,
            proteins = protein,
            fats = fats,
            imagePath = "",  // Modify based on your needs
            timestamp = System.currentTimeMillis()
        )
    }
}
