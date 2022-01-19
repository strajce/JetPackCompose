package com.example.jetpackcompose.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcompose.R

@Composable
fun BottomBar(
    navigationController: NavController
) {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navigationController
                    .navigate(R.id.action_recipeListFragment_to_recipeAnimationFragment)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = {
                navigationController
                    .navigate(R.id.action_recipeListFragment_to_rocketFragment)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            }
        )
    }
}