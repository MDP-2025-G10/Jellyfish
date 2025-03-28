package com.example.mdp.ui.components.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.mdp.data.model.Meal
import com.example.mdp.data.viewmodel.MealViewModel


@Composable
fun FoodPopUp(
    mealViewModel: MealViewModel,
    meal: Meal,
    onDismiss: () -> Unit
) {
    var mealName by remember { mutableStateOf(meal.name) }
    val calories by remember { mutableStateOf(meal.calories.toString()) }
    val fats by remember { mutableStateOf(meal.fats.toString()) }
    val carbs by remember { mutableStateOf(meal.carbs.toString()) }
    val protein by remember { mutableStateOf(meal.proteins.toString()) }

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
                    value = calories,
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Calories (kcal)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = fats,
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Fats (g)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = carbs,
                    onValueChange = { it.toIntOrNull() ?: 0 },
                    label = { Text("Carbs (g)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = protein,
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
                        calories = calories.toIntOrNull() ?: 0,
                        fats = fats.toIntOrNull() ?: 0,
                        carbs = carbs.toIntOrNull() ?: 0,
                        proteins = protein.toIntOrNull() ?: 0,
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


//private fun getNutrientValue(food: FoodItem, nutrientName: String): String {
//    return food.nutrients.find { it.name.contains(nutrientName, true) }
//        ?.value?.toInt()?.toString() ?: "0"
//}

