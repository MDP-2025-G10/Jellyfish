package com.example.mdp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import com.example.mdp.ui.components.food.HistorySection
import com.example.mdp.ui.components.food.SearchBar
import com.example.mdp.ui.components.food.SuggestionSection
import com.example.mdp.ui.components.toolbar.BottomBar
import com.example.mdp.ui.components.toolbar.TopBar
import com.example.mdp.usda.viewmodel.FoodViewModel
import com.example.mdp.data.viewmodel.MealViewModel
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
    val allMealList by mealViewModel.allMealList.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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
            SearchBar(searchQuery) { foodViewModel.updateSearchQuery(it) }

            val tabs = listOf("History", "Suggestions")
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> HistorySection(allMealList, mealViewModel)
                1 -> SuggestionSection(foodList, mealViewModel)
            }
        }
    }
}

