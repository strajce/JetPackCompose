package com.example.jetpackcompose.presentation.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackcompose.presentation.ui.recipe.RecipeDetailsEvent.GetRecipeEvent
import com.example.jetpackcompose.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
            Log.d(TAG, "Selected ID: ${recipeId}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value

                Column {
                    Text(
                        text = recipe?.let {
                            "Selected recipe id : ${recipe.id}"
                        } ?: "loading ...",
                        style = TextStyle(
                            fontSize = 21.sp,
                        )
                    )
                    Text(
                        text = "${recipe?.title}",
                        style = TextStyle(
                            fontSize = 21.sp,
                        )
                    )
                }
            }
        }
    }
}