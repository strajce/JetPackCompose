package com.example.jetpackcompose.presentation.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.jetpackcompose.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val animationState = remember { mutableStateOf(false) }
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                ) {
                    Rocket(
                        isRocketEnabled = animationState.value,
                        maxWidth = maxWidth,
                        maxHeight = maxHeight
                    )
                }
                LaunchButton(animationState = animationState.value,
                    onToggleAnimationState = {
                        animationState.value = !animationState.value
                    })
            }
        }
    }
}

@Composable
fun Rocket(
    isRocketEnabled: Boolean,
    maxWidth: Dp,
    maxHeight: Dp
) {
    val resource: Painter
    val rocketSize = 200.dp
    val infiniteTransition = rememberInfiniteTransition()
    val modifier: Modifier
    if (!isRocketEnabled) {
        resource = painterResource(id = R.drawable.rocket_intial)
        modifier = Modifier.offset(
            y = maxHeight - rocketSize,
        )
    } else {
        val engineState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = LinearEasing
                )
            )
        )
        val positionState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis =10000,
                    easing = LinearEasing
                )
            )
        )

        resource = if (engineState.value <= .5f) {
            painterResource(id = R.drawable.rocket1)
        } else {
            painterResource(id = R.drawable.rocket2)
        }
        modifier = Modifier.offset(
            x = (maxWidth - rocketSize) * positionState.value,
            y = (maxHeight - rocketSize) - ((maxHeight - rocketSize) * positionState.value),
        )
    }
    Image(
        modifier = modifier
            .width(rocketSize)
            .height(rocketSize),
        painter = resource,
        contentDescription = "A Rocket"
    )
}

@Composable
fun LaunchButton(
    animationState: Boolean,
    onToggleAnimationState: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (animationState) {
            Button(
                onClick = onToggleAnimationState,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "STOP")

            }
        } else {
            Button(onClick = onToggleAnimationState) {
                Text(text = "LAUNCH")
            }
        }
    }
}