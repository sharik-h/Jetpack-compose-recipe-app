package com.example.recipebook.CustomComposes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryItem(
    value: String,
    onClick: () -> Unit,
    onSelect: () -> Unit
) {
    Row(modifier = Modifier
        .clickable { onClick() }
        .fillMaxWidth()
        .height(64.dp)
        .padding(all = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.History, contentDescription = null)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = value, modifier = Modifier.weight(0.2f))
        IconButton(onClick = { onSelect() }) {
            Icon(imageVector = Icons.Default.ArrowOutward, contentDescription = "")
        }
    }
}