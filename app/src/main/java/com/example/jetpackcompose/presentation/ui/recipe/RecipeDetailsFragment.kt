package com.example.jetpackcompose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackcompose.presentation.BaseApplication
import com.example.jetpackcompose.presentation.components.CircularProgressBar
import com.example.jetpackcompose.presentation.components.RecipeView
import com.example.jetpackcompose.presentation.theme.AppTheme
import com.example.jetpackcompose.presentation.ui.recipe.RecipeDetailsEvent.GetRecipeEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = application.isDark.value) {
                    val loading = viewModel.loading.value
                    val recipe = viewModel.recipe.value

                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        item {
                            recipe?.let {
                                RecipeView(recipe = recipe)
                            } ?: CircularProgressBar(isDisplayed = loading)
                        }
                    }
                }
            }
        }

    }
}