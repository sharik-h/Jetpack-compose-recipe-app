package com.example.recipebook.CustomComposes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomNumberPicker(
    onSelect: (Int) -> Unit
) {

    var number by remember { mutableIntStateOf(1) }

    Surface(
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(50)
    ) {
        Row(
            modifier = Modifier
                .width(160.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    number--
                    onSelect(number)
                },
                enabled = number > 1,
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(30.dp)
            ) {
                Icon(imageVector = Icons.Default.HorizontalRule, contentDescription = null)
            }
            Text(text = number.toString(), fontWeight = FontWeight.Bold)
            IconButton(
                onClick = {
                    number++
                    onSelect(number)
                },
                enabled = number < 10,
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(30.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}