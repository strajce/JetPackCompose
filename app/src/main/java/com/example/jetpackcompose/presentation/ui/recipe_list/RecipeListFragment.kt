package com.example.jetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.components.RecipeCard
import com.example.jetpackcompose.util.TAG
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

                LazyColumn{
                     itemsIndexed(
                         items = recipes
                     ) {
                         index, recipe ->
                         RecipeCard(recipe = recipe, onClick = {})
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