package com.example.recipebook.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun ViewRecipe(onclick: () -> Unit) {

    val ingrediants = mapOf("coffee powder" to "1 teaSpoon", "water" to "1/2 cup", "sugar" to "1 teaSpoon", "milk" to "1/2 cup")
    val procedure = makeCoffee()

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.DeleteOutline, contentDescription = "")
                }
            },
            navigationIcon = {
                IconButton(onClick = { onclick() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="")
                }
            }
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Text(text = "Homemade Waffle", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Column(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(5.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(20))
                        .background(color = Color(0xFF9FC6D3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Outlined.Timer, contentDescription = "",modifier = Modifier.size(35.dp))
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "39 min", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(5.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(20))
                        .background(color = Color(0xFF9FC6D3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                shape = CircleShape,
                                color = Color.Black
                            )
                            .size(35.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "2", fontSize = 20.sp, fontWeight = FontWeight.Bold,)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Serving", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(5.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(20))
                        .background(color = Color(0xFF9FC6D3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // condition for different level and icons according to it is needed
                    Icon(imageVector = Icons.Default.Face, contentDescription = "", modifier = Modifier.size(35.dp))
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Beginner", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }

            Text(text = "Ingrediants", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            ingrediants.forEach {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = it.key, fontSize = 20.sp)
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text(text = it.value, fontSize = 20.sp)
                }
            }
            Text(text = "Steps", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            procedure.forEach {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = Color(0xFF9FC6D3), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${procedure.indexOf(it) +  1}", color = Color.White, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = it, fontSize = 20.sp)
                }
            }
        }
    }
}

fun makeCoffee(): List<String> {
    val steps = mutableListOf<String>()

    steps.add("Grind coffee beans.")
    steps.add("Boil water.")
    steps.add("Add ground coffee to water.")
    steps.add("Let coffee steep for 4 minutes.")
    steps.add("Pour coffee into a cup.")
    steps.add("Enjoy your coffee!")

    return steps
}