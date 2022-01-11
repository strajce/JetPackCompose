package com.example.jetpackcompose.network.responses

import com.example.jetpackcompose.network.model.RecipeDTO
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse(
    var count: Int,
    @SerializedName("results")
    var recipe: List<RecipeDTO>
)