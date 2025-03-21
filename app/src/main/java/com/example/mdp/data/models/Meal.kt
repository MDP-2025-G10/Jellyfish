package com.example.mdp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Auto-increment primary key
    val name: String,  // Meal name
    val calories: Int,  // Calories count
    val fats: Int,  // Fat content (grams)
    val carbs: Int,  // Carbohydrates (grams)
    val proteins: Int,  // Protein content (grams)
    val imagePath: String,  // Path to the saved image
    val timestamp: Long  // Time when meal was added
)