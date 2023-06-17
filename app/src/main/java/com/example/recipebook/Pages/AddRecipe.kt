package com.example.recipebook.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipebook.CustomComposes.Bubble
import com.example.recipebook.model.Recipe


@Composable
fun AddRecipe(
    top: (Boolean) -> Unit,
    recipe:(Recipe) -> Unit
) {
    var state by remember { mutableStateOf(false) }

    Bubble(
        activeContent = {
            AddPage()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {
                        state = false
                        top(false)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")
                }
            }
        },
        inactiveContent = {
            FloatingActionButton(
                onClick = {
                    state = true

                    top(true) },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        states = state
    )
}