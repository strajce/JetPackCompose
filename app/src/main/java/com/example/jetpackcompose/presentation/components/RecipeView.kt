package com.example.jetpackcompose.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.util.DEFAULT_RECIPE_IMAGE
import com.example.jetpackcompose.util.loadPicture

@Composable
fun RecipeView(
    recipe: Recipe,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        recipe.featuredImage?.let { url ->
            val image =
                loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(), contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(250.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        recipe.title?.let { title ->
            Text(
                text = title,
                modifier = Modifier.padding(6.dp),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )
        }
        Text(
            text = "Ingredients :",
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface
        )
        Column(
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp, bottom = 6.dp)
                .border(
                    BorderStroke(2.dp, Color.LightGray),
                    shape = MaterialTheme.shapes.medium
                ).background(MaterialTheme.colors.surface)
        ) {
            recipe.ingredients?.let { ingredients ->
                for (ingredient in ingredients) {
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSecondary,
                    )
                }
            }
        }
    }
}