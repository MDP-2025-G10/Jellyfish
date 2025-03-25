package com.example.mdp.ui.screens

import android.util.Log
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar
import com.example.mdp.ui.window.FoodPopUp
import com.example.mdp.usda.model.FoodItem
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import com.example.mdp.usda.viewmodel.FoodViewModel
import com.example.mdp.viewmodels.MealViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun Food(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel(),
    mealViewModel: MealViewModel = koinViewModel(),
    foodViewModel: FoodViewModel = koinViewModel()
) {

    val foodList by foodViewModel.foodList.collectAsState()
    val searchQuery by foodViewModel.searchQuery.collectAsState()
    var searchJob: Job? = null
    val allMealList by mealViewModel.allMealList.collectAsState()

    LaunchedEffect(searchQuery) {
        searchJob?.cancel()
        searchJob = launch {
            delay(500L)
            if (searchQuery.isNotEmpty()) {
                foodViewModel.searchFood(searchQuery)
            }
        }
    }

    Scaffold(
        topBar = { TopBar(navController, authViewModel) },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { foodViewModel.updateSearchQuery(it) },
                label = { Text("Search for food") },
                modifier = Modifier.fillMaxWidth()
            )

            Column(modifier = Modifier.weight(0.5f)) {
                Text("History", style = MaterialTheme.typography.titleLarge)
                Log.d("allMealList","$allMealList")
                if (allMealList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(foodList) { foodItem ->
                            FoodItem(foodItem, mealViewModel)
                        }
                    }
                } else {
                    Text("No food found.", modifier = Modifier.align(Alignment.Start))
                }
            }

            Column(modifier = Modifier.weight(0.5f)) {
                Text("Suggestion", style = MaterialTheme.typography.titleLarge)
                Log.d("allMealList","$foodList")
                if (foodList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(foodList) { foodItem ->
                            FoodItem(foodItem, mealViewModel)
                        }
                    }
                } else {
                    Text("No food found.", modifier = Modifier.align(Alignment.Start))
                }
            }
        }
    }
}

@Composable
fun FoodItem(food: FoodItem, mealViewModel: MealViewModel) {
    var showPopup by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(modifier = Modifier.weight(0.9f)) {
                Text(food.name, style = MaterialTheme.typography.titleMedium)
                val calories = food.nutrients.find { it.name.contains("Energy", true) }?.value ?: 0f
                val size = food.size.toInt()
                val carbs =
                    food.nutrients.find { it.name.contains("Carbohydrate", true) }?.value ?: 0f
                val protein = food.nutrients.find { it.name.contains("Protein", true) }?.value ?: 0f
                val fats = food.nutrients.find { it.name.contains("Fat", true) }?.value ?: 0f

                Text("Calories: ${calories.toInt()} kcal / $size g")
                Text("Carbs: ${carbs}g")
                Text("Protein: ${protein}g")
                Text("Fats: ${fats}g")
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
            food = food,  // Pass the selected food
            onDismiss = { showPopup = false }
        )
    }
}