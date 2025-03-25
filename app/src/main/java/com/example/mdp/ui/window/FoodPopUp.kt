package com.example.mdp.ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.mdp.usda.model.FoodItem
import com.example.mdp.data.model.Meal
import com.example.mdp.viewmodels.MealViewModel


@Composable
fun FoodPopUp(
    mealViewModel: MealViewModel,
    food: FoodItem,
    onDismiss: () -> Unit
) {

    var mealName by remember { mutableStateOf(food.name) }
    val calories by remember {
        mutableIntStateOf(
            getNutrientValue(food, "Energy").toIntOrNull() ?: 0
        )
    }
    val fats by remember { mutableIntStateOf(getNutrientValue(food, "Fat").toIntOrNull() ?: 0) }
    val carbs by remember {
        mutableIntStateOf(
            getNutrientValue(food, "Carbohydrate").toIntOrNull() ?: 0
        )
    }
    val proteins by remember {
        mutableIntStateOf(
            getNutrientValue(food, "Protein").toIntOrNull() ?: 0
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Meal") },
        text = {
            Column {
                OutlinedTextField(
                    value = mealName,
                    onValueChange = { mealName = it },
                    label = { Text("Meal Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = calories.toString(),
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Calories (kcal)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = fats.toString(),
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Fats (g)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = carbs.toString(),
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Carbs (g)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = proteins.toString(),
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Proteins (g)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

            }

        },
        confirmButton = {
            Button(onClick = {
                if (mealName.isNotEmpty()) {
                    val newMeal = Meal(
                        name = mealName,
                        calories = calories,
                        fats = fats,
                        carbs = carbs,
                        proteins = proteins,
                        imagePath = "",
                        timestamp = System.currentTimeMillis()
                    )
                    mealViewModel.insertMeal(newMeal)
                    onDismiss()
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


private fun getNutrientValue(food: FoodItem, nutrientName: String): String {
    return food.nutrients.find { it.name.contains(nutrientName, true) }
        ?.value?.toInt()?.toString() ?: "0"
}

