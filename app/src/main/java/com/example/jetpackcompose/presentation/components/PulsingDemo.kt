package com.example.jetpackcompose.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary

}
object PulseAnimationDefinitions {
    enum class PulseState{
        INITIAL, FINAL
    }

}