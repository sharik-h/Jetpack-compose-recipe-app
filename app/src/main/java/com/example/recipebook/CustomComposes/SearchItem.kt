package com.example.recipebook.CustomComposes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchItem(
    value: String,
    onclick: () -> Unit
) {
    Row(modifier = Modifier
        .padding(all = 14.dp)
        .clickable { onclick() }
    ) {
        Icon(imageVector = Icons.Default.OpenInFull, contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = value)
    }
}