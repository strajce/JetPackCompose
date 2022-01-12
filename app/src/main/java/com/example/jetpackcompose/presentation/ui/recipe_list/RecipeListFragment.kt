package com.example.jetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jetpackcompose.presentation.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    //    val viewModel: RecipeListViewModel by viewModels()
    val viewModel: RecipeListViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        println("RecipeListFragment: ${viewModel}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        viewModel.recipes.observe(viewLifecycleOwner, { recipes ->
//        })

        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value
//                val query = remember {
//                    mutableStateOf("beef")
//                }
                val query = viewModel.query.value

                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.primary,
                        elevation = 8.dp,
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(0.95f)
                                    .padding(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.secondary,
                                ),
                                value = query,
                                onValueChange = { newValue ->
                                    viewModel.onQueryChange(newValue)
                                },
                                label = {
                                    Text(text = "Search")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search,
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null,
                                    )
                                },
                                keyboardActions = KeyboardActions(onSearch = {
                                    viewModel.newSearch(
                                        query = query,
                                    )
                                    clearFocus()
                                }),
                            )
                        }
                    }
//                    TextField(
//                        value = query,
//                        onValueChange = { newValue ->
//                            viewModel.onQueryChange(newValue)
//                        },
//                    )

                    Spacer(Modifier.padding(10.dp))

                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})
                        }
                    }
                }

//                for (recipe in recipes) {
//                    Log.d(TAG, "onCreateView: ${recipe.title}")
//                }
//                Column(Modifier.padding(16.dp)) {
//                    Text(
//                        text = "Recipe List",
//                        style = TextStyle(
//                            fontSize = 24.sp
//                        )
//                    )
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    Button(onClick = { findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment) }) {
//                        Text(text = "To Recipe Fragment")
//                    }
//
//                }
            }
        }
    }
}