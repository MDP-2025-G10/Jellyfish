package com.example.mdp.usda.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdp.usda.model.FoodItem
import com.example.mdp.usda.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository) : ViewModel() {

    private val _foodList = MutableStateFlow<List<FoodItem>>(emptyList())
    val foodList: StateFlow<List<FoodItem>> = _foodList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val originalFoodList = mutableListOf<FoodItem>()

    init {
        observeSearchQuery()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        searchQuery
            .debounce(500L)  // Wait 500ms
            .distinctUntilChanged()  // Avoid duplicate queries
            .filter { it.isNotEmpty() }  // Ignore empty queries
            .onEach { query -> searchFood(query) }  // Perform search
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }


    private fun searchFood(query: String = "rice") {
        viewModelScope.launch {
            try {
                val response = repository.searchFood(query)
                originalFoodList.clear()
                originalFoodList.addAll(response.foods)
                Log.d("FoodViewModel", "${response.foods}")
                _foodList.value = originalFoodList // Update displayed list
            } catch (e: Exception) {
                println("Error search foods: ${e.message}")
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _foodList.value = originalFoodList.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}
