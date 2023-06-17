package com.example.recipebook.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.CustomComposes.AnimatedSelector
import com.example.recipebook.CustomComposes.CustomNumberPicker
import com.example.recipebook.CustomComposes.ImageDropDown
import com.example.recipebook.CustomComposes.CustomTextField
import com.example.recipebook.R

@Preview(showBackground = true)
@Composable
fun AddPage() {

    var name by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val items = remember { mutableListOf<String>() }
    var serving by remember { mutableIntStateOf(1) }
    var timeType by remember { mutableStateOf("Minutes") }
    var newItem by remember { mutableStateOf(false) }
    var step by remember { mutableStateOf(false) }
    val steps = remember { mutableListOf<String>() }
    val hardness = remember { mutableStateOf("Beginner") }
    val levels = listOf("Beginner", "Medium", "Hard")
    var image by remember { mutableIntStateOf(R.drawable.burger) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Recipe",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(0.1f))
            ImageDropDown { image = it }
        }
        CustomTextField(holderValue = "Name") { name = it }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(holderValue = "Time", onDone = { time = it }, modifier = Modifier.width(200.dp))
            Spacer(modifier = Modifier.width(20.dp))
            AnimatedSelector(items = listOf("Minutes", "Hours")){ timeType = it }
        }
        AnimatedSelector(items = levels){ hardness.value = it }
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "servings", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(50.dp))
            CustomNumberPicker{ serving = it }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Items:", fontSize = 20.sp)
        items.forEach { item ->
            CustomTextField(value1 = item) { items[items.indexOf(it)] = it }
        }
        if (!newItem) {
            IconButton(onClick = { newItem = true }) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
        } else {
            CustomTextField(holderValue = "Item") {
                items.add(it)
                newItem = false
            }
        }

        Text(text = "Steps", fontSize = 20.sp)
        steps.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Step ${steps.indexOf(it) + 1}")
                CustomTextField(value1 = it) {
                    steps[steps.indexOf(it)] = it
                }
            }
        }
        if (!step) {
            IconButton(onClick = { step = true }) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Step ${steps.size + 1}", fontSize = 20.sp)
                CustomTextField {
                    steps.add(it)
                    step = false
                }
            }
        }
    }
}