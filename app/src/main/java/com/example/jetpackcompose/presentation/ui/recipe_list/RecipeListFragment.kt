package com.example.jetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.components.CircularProgressBar
import com.example.jetpackcompose.presentation.components.RecipeCard
import com.example.jetpackcompose.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value

                Column(
//                    modifier = Modifier.padding(8.dp),
                ) {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChange,
                        onExecuteSearch = viewModel::newSearch,
                        categoryPosition = viewModel.categoryPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        clearKeyBoardFocus = KeyboardActions(onSearch = {
                            clearFocus()
                        })
                    )

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.padding(top = 6.dp)
                        ) {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe = recipe, onClick = {
                                    findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment)
//                                    Toast.makeText(context, "Index : ${index}", Toast.LENGTH_SHORT)
//                                        .show()
                                })
                            }
                        }
                        CircularProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }
}