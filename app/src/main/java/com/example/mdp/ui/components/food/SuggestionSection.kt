package com.example.mdp.ui.components.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mdp.usda.model.FoodItem
import com.example.mdp.utils.FoodMapper
import com.example.mdp.data.viewmodel.MealViewModel

@Composable
fun SuggestionSection(foodList: List<FoodItem>, mealViewModel: MealViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (foodList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(foodList) { foodItem ->
                    SuggestionCard(foodItem, mealViewModel)
                }
            }
        } else {
            Text("Try to search something?", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


@Composable
fun SuggestionCard(food: FoodItem, mealViewModel: MealViewModel) {
    var showPopup by remember { mutableStateOf(false) }

    val meal = FoodMapper.convertFoodItemToMeal(food)

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(modifier = Modifier.weight(0.9f)) {
                Text(meal.name, style = MaterialTheme.typography.titleMedium)
                Text("Calories: ${meal.calories} kcal")
                Text("Carbs: ${meal.carbs}g")
                Text("Protein: ${meal.proteins}g")
                Text("Fats: ${meal.fats}g")
            }
            Column(modifier = Modifier.weight(0.1f)) {
                IconButton(onClick = { showPopup = true }) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Add new item",
                        tint = Color(0xFF5A67B4),
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }
    }

    if (showPopup) {
        FoodPopUp(
            mealViewModel,
            meal = FoodMapper.convertFoodItemToMeal(food), // Convert FoodItem to Meal
            onDismiss = { showPopup = false }
        )
    }
}