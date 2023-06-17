package com.example.recipebook.CustomComposes

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Bubble(
    activeContent: @Composable (() -> Unit),
    inactiveContent: @Composable (() -> Unit),
    states: Boolean
) {

    var state by remember { mutableStateOf(false) }
    var tempRadius by remember { mutableIntStateOf(0) }
    var tempSize by remember { mutableStateOf(0.dp) }
    val radius by animateIntAsState(targetValue = tempRadius, keyframes { durationMillis = 300 })
    val height by animateDpAsState(targetValue = tempSize, keyframes { durationMillis = 300 })

    if (states){
        state = states
        tempRadius = 0
        tempSize = 1000.dp
    }else{
        if (radius == 50) {
            state = states
        }
        tempRadius = 50
        tempSize = 0.dp
    }


    val modifier = if (state){
        Modifier
            .size(height)
            .clip(RoundedCornerShape(radius))
    }else{
        Modifier
            .padding(10.dp)
            .size(50.dp)
    }

    Box(modifier = modifier) {
        if (state){
            activeContent()
        }else{
            inactiveContent()
        }
    }
}