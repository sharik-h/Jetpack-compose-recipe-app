package com.example.recipebook.Pages

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipe(
    recipe: Recipe?,
    onclick: () -> Unit
) {

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
            Row(Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = recipe!!.img.toInt()), contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = recipe.name, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }
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
                    Text(text = recipe?.time.toString(), fontSize = 15.sp, fontWeight = FontWeight.Bold)
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
                        Text(text = recipe?.serving.toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold,)
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
                        .background(
                            color = when (recipe?.level) {
                                "begginer" -> {
                                    Color(0xFF9FD3A4)
                                }

                                "medium" -> {
                                    Color(0xFFD3CC9F)
                                }

                                else -> {
                                    Color(0xFFD39FA7)
                                }
                            }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // condition for different level and icons according to it is needed

                    Icon(imageVector = Icons.Default.Face, contentDescription = "", modifier = Modifier.size(35.dp))
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = recipe?.level.toString(), fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }

            Text(text = "Ingrediants", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            recipe?.items?.forEach {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = it.first, fontSize = 20.sp)
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text(text = it.second, fontSize = 20.sp)
                }
            }
            Text(text = "Steps", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            recipe!!.procedure.forEach {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = Color(0xFF9FC6D3), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${it.indexOf(it) +  1}", color = Color.White, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = it, fontSize = 20.sp)
                }
            }
        }
    }
}