package com.example.jetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.BaseApplication
import com.example.jetpackcompose.presentation.components.*
import com.example.jetpackcompose.presentation.theme.AppTheme
import com.example.jetpackcompose.presentation.ui.recipe_list.RecipeListEvent.NextPageEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(
                    darkTheme = application.isDark.value,
                ) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val loading = viewModel.loading.value
                    val page = viewModel.page.value

                    Column {
                        Scaffold(
                            topBar = {
                                SearchAppBar(
                                    query = query,
                                    onQueryChanged = viewModel::onQueryChange,
                                    onExecuteSearch = viewModel::onTriggerEvent,
                                    categoryPosition = viewModel.categoryPosition,
                                    selectedCategory = selectedCategory,
                                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                    clearKeyBoardFocus = KeyboardActions(onSearch = {
                                        clearFocus()
                                    }),
                                    onToggleTheme = {
                                        application.toggleLightTheme()
                                    }
                                )
                            },
                            bottomBar = {
                                BottomBar(findNavController())
                            },
                            drawerContent = {
                                DrawerMenu()
                            },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = MaterialTheme.colors.background)
                            ) {
                                LazyColumn(
                                    modifier = Modifier.padding(top = 6.dp)
                                ) {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        viewModel.onChangeRecipeScrollPosition(index)
                                        if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                                            viewModel.onTriggerEvent(NextPageEvent)
                                        }

                                        RecipeCard(recipe = recipe, onClick = {
                                            val bundle = Bundle()
                                            recipe.id?.let { it1 -> bundle.putInt("recipeId", it1) }
                                            findNavController()
                                                .navigate(R.id.action_recipeListFragment_to_recipeDetailsFragment, bundle)
//                                            Toast.makeText(
//                                                context,
//                                                "Index number ${index}",
//                                                Toast.LENGTH_SHORT
//                                            ).show()
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
    }
}