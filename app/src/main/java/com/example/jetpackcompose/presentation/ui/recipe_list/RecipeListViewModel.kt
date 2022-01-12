package com.example.jetpackcompose.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {

    init {
        println("VIEWMODEL : ${randomString}")
        println("VIEWMODEL : ${repository}")
        println("VIEWMODEL : ${token}")
    }
}