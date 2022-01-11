package com.example.jetpackcompose.network.responses

import com.example.jetpackcompose.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    var count: Int,
    @SerializedName("results")
    var recipe: List<RecipeDto>
)