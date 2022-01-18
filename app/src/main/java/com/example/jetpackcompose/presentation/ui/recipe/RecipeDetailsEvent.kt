package com.example.jetpackcompose.presentation.ui.recipe

sealed class RecipeDetailsEvent {
    data class GetRecipeEvent(
        val id: Int
    ) : RecipeDetailsEvent()
}