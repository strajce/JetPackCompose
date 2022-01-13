package com.example.jetpackcompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.presentation.ui.recipe_list.FoodCategory
import com.example.jetpackcompose.presentation.ui.recipe_list.getAllFoodCategories

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categoryPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    clearKeyBoardFocus: KeyboardActions,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        elevation = 8.dp,
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.LightGray,
                    ),
                    value = if(query == FoodCategory.ALL.toString()) "" else query,
                    onValueChange = { newValue ->
                        onQueryChanged(newValue)
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
                        onExecuteSearch()
                        clearKeyBoardFocus
                    }),
                )
            }
            ScrollableTabRow(
                modifier = Modifier.padding(8.dp),
                selectedTabIndex = categoryPosition,
                edgePadding = 0.dp,
                backgroundColor = Color.White,
                tabs = {
                    for (category in getAllFoodCategories()) {
                        FoodCategoryChip(
                            category = category.value,
                            isSelected = selectedCategory == category,
                            onSelectedCategoryChanged = {
                                onSelectedCategoryChanged(it)
                            },
                            onExecuteSearch = {
                                onExecuteSearch()
                            }
                        )
                    }
                },
            )
        }
    }
}