package com.example.jetpackcompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.ui.recipe_list.RecipeListFragment
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var someRandomString: String

    @Inject
    lateinit var app: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        Log.d(TAG, "onCreate ${someRandomString}")
//        Log.d(TAG, "onCreate ${app}")

//        val service = Retrofit.Builder()
//            .baseUrl("https://food2fork.ca/api/recipe/")
//            .addConverterFactory(
//                GsonConverterFactory
//                    .create(
//                        GsonBuilder()
//                            .create()
//                    )

//            )
//            .build()
//            .create(RecipeService::class.java)
//
//        CoroutineScope(IO).launch {
//            val recipe = service.get(
//                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
//                id = 583
//            )
//            Log.d("MainActivity", "onCreate: ${recipe.title}")
//        }

//        val mapper = RecipeNetworkMapper()
//
//        val recipe = Recipe()
//
//        val networkEntity: RecipeDTO = mapper.mapToEntity(recipe)
//
//        val tempRecipe: Recipe = mapper.mapFromEntity(networkEntity)

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