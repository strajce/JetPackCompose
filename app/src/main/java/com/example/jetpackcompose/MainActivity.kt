package com.example.jetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.domain.model.Recipe
import com.example.jetpackcompose.network.model.RecipeDTO
import com.example.jetpackcompose.network.model.RecipeNetworkMapper
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapper = RecipeNetworkMapper()

        val recipe = Recipe()

        val networkEntity: RecipeDTO = mapper.mapToEntity(recipe)

        val tempRecipe: Recipe = mapper.mapFromEntity(networkEntity)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_nav_host_fragment, RecipeListFragment())
//            .commit()
//        setContent {
//            JetPackComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Greeting("World")
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeTheme {
        Column {
            Greeting("Android")
            Divider()
            Greeting("World")
        }
    }
}