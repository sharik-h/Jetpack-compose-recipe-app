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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.CustomComposes.AnimatedSelector
import com.example.recipebook.CustomComposes.CustomNumberPicker
import com.example.recipebook.CustomComposes.CustomTextField
import com.example.recipebook.CustomComposes.ImageDropDown
import com.example.recipebook.R
import com.example.recipebook.data.MainViewModel
import com.example.recipebook.model.Recipe

@Composable
fun AddPage(
    viewModel: MainViewModel,
    newRecipe: Recipe?
) {
    var newItem by remember { mutableStateOf(false) }
    var step by remember { mutableStateOf(false) }
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
            ImageDropDown(
                selected = newRecipe?.image,
                onclick = { viewModel.setnewRecipie(name = "image", value = it) }
            )
        }
        CustomTextField(
            holderValue = "Name",
            value1 = newRecipe?.name ?: "",
            onDone = {
                viewModel.setnewRecipie(name = "name", value = it)
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                holderValue = "Time",
                value1 = newRecipe?.time ?: "",
                onDone = { viewModel.setnewRecipie("time",it) } ,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            AnimatedSelector(
                items = listOf("Minutes", "Hours"),
                select = newRecipe?.timeType,
                onSelect = { viewModel.setnewRecipie("timeType", it)  }
            )
        }
        AnimatedSelector(
            items = levels,
            select = newRecipe?.level,
            onSelect = { viewModel.setnewRecipie("level", it) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "servings", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(50.dp))
            CustomNumberPicker(
                selected = if(newRecipe?.serving != "") newRecipe!!.serving.toInt()  else 1,
                onSelect = { viewModel.setnewRecipie("servings", it.toString()) }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Items:", fontSize = 20.sp)
        newRecipe?.items?.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = item.first, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.1f))
                Text(text = item.second,fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.1f))
                IconButton(onClick = { viewModel.deleteItem(item) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
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
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextField(
                    holderValue = "Item",
                    modifier = Modifier.weight(0.1f),
                    onDone = {
                        viewModel.setnewRecipie("itemName", it)
                    }
                )
                CustomTextField(
                    holderValue = "Qty",
                    modifier = Modifier.weight(0.1f),
                    onDone = {
                        viewModel.setnewRecipie("itemQty", it)
                    }
                )
                IconButton(onClick = {
                    viewModel.addItems()
                    newItem = false
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.weight(0.1f))
                }
            }
        }

        Text(text = "Steps", fontSize = 20.sp)
        newRecipe?.procedure?.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Step ${newRecipe.procedure.indexOf(it) + 1}")
                CustomTextField(value1 = it, onDone =  {} )
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
                Text(
                    text = "Step ${(newRecipe?.procedure?.size ?: 0) + 1}",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(0.1f)
                )
                CustomTextField(
                    onDone = { viewModel.setnewRecipie("step", it) },
                    modifier = Modifier.weight(0.3f)
                )
                IconButton(
                    modifier = Modifier.weight(0.1f),
                    onClick = {
                        viewModel.addStep()
                        step = false
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }
}