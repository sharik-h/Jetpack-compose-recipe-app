package com.example.recipebook.CustomComposes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedSelector(
    items: List<String>,
    select: String?,
    onSelect: (String) -> Unit
) {

    val len = items.size

    var tempBefore by remember { mutableFloatStateOf(0.0f) }
    var tempAfter by remember { mutableFloatStateOf((0.1 * (len - 1)).toFloat()) }
    if (select != ""){
        tempBefore = (0.1 * items.indexOf(select)).toFloat()
        tempAfter =
            (0.1 * (items.lastIndex - (items.indexOf(select)))).toFloat()
    }
    val beforeSelection by animateFloatAsState(
        targetValue = tempBefore,
        keyframes { durationMillis = 300 }
    )
    val afterSelection by animateFloatAsState(
        targetValue = tempAfter,
        keyframes { durationMillis = 300 }
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        shape = RoundedCornerShape(50),
        shadowElevation = 3.dp
    ) {
        Row(Modifier.fillMaxSize()) {
            if (beforeSelection > 0.0f) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(beforeSelection)
                        .background(Color.White)
                        .animateContentSize()
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(0.1f)
                    .background(Color(0xFFE6ACB6))
            )
            if (afterSelection != 0.0f) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(afterSelection)
                        .background(Color.White)
                        .animateContentSize()
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach {
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .clickable {
                                tempBefore = (0.1 * items.indexOf(it)).toFloat()
                                tempAfter =
                                    (0.1 * (items.lastIndex - (items.indexOf(it)))).toFloat()
                                onSelect(it)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}