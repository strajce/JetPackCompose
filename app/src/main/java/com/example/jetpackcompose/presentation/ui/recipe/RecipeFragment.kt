package com.example.jetpackcompose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    val viewModel: RecipeListViewModel by activityViewModels()
    val isVisible = mutableStateOf(true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("RecipeFragment: ${viewModel}")
    }

    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
//                AnimatedVisibility(
//                    visible = true,
//                    enter = fadeIn(),
//                    exit = fadeOut(),
//                ) {
//                    Column(androidx.compose.ui.Modifier.padding(16.dp)) {
//                        Text(
//                            text = "Recipe Fragment",
//                            style = TextStyle(
//                                fontSize = 24.sp,
//                                background = Color.Cyan,
//                                fontStyle = FontStyle.Italic
//                            )
//                        )
//                    }
//                }
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    var count by remember { mutableStateOf(0) }
                    Button(onClick = {
                        count++
                    }) {
                        Text(text = "Count forward")
                    }
                    Divider(modifier = Modifier.padding(24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedContent(
                            modifier = Modifier.padding(16.dp),
                            targetState = count,
                            transitionSpec = {
                                // Compare the incoming number with the previous number.
                                if (targetState > initialState) {
                                    // If the target number is larger, it slides up and fades in
                                    // while the initial (smaller) number slides up and fades out.
                                    slideInVertically { height -> height } + fadeIn() with
                                            slideOutVertically { height -> -height } + fadeOut()
                                } else {
                                    // If the target number is smaller, it slides down and fades in
                                    // while the initial number slides down and fades out.
                                    slideInVertically { height -> -height } + fadeIn() with
                                            slideOutVertically { height -> height } + fadeOut()
                                }.using(
                                    // Disable clipping since the faded slide-in/out should
                                    // be displayed out of bounds.
                                    SizeTransform(clip = false)
                                )
                            }
                        ) { targetCount ->
                            Text(
                                text = "$targetCount",
                                style = TextStyle(fontSize = 60.sp),
                                fontWeight = FontWeight(60),
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(24.dp))
                    Button(onClick = {
                        count = if (count > 0) count - 1 else 0
                    }) {
                        Text(text = "Count backward")
                    }
                    Divider(modifier = Modifier.padding(24.dp))
                    Button(onClick = {
                        count = 0
                    }) {
                        Text("Reset counter")
                    }
                }
            }
        }
    }
}