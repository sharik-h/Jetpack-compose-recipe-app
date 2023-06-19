package com.example.recipebook.CustomComposes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageDropDown(
    onclick: (String) -> Unit
) {

    var open by remember { mutableStateOf(false) }
    val images = images()
    var selectedImage by remember { mutableIntStateOf(images.random().second) }
    Surface(
        shape = RoundedCornerShape(30),
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .width(70.dp)
                .clickable { open = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = selectedImage), contentDescription = null)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
            images.forEach {
                DropdownMenuItem(
                    text = {
                        Image(painter = painterResource(id = it.second), contentDescription = null)
                    },
                    onClick = {
                        open = false
                        selectedImage = it.second
                        onclick(it.first)
                    }
                )
            }
        }
    }

}