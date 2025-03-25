package com.example.mdp.usda.model

import com.google.gson.annotations.SerializedName

data class FoodSearchResponse(
    @SerializedName("foods") val foods: List<FoodItem>
)

data class FoodItem(
    @SerializedName("fdcId") val id: Int,
    @SerializedName("description") val name: String,
    @SerializedName("brandOwner") val brand: String,
    @SerializedName("servingSize") val size: Float,
    @SerializedName("servingSizeUnit") val sizeUnit: String,
    @SerializedName("foodNutrients") val nutrients: List<Nutrient>
)

data class Nutrient(
    @SerializedName("nutrientName") val name: String,
    @SerializedName("value") val value: Float,
    @SerializedName("unitName") val unit: String
)