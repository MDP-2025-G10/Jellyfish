package com.example.mdp.usda.repository

import com.example.mdp.usda.USDAApiService
import com.example.mdp.usda.model.FoodSearchResponse

class FoodRepository(private val apiService: USDAApiService) {

    suspend fun searchFood(query: String): FoodSearchResponse {
        return apiService.searchFood(query)
    }
}
