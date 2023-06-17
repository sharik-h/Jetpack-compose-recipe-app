package com.example.recipebook.CustomComposes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.R

@Composable
fun RecipeUi(
    img: Painter,
    name: String,
    time: String,
    onclick: () -> Unit
) {

    val colors = colors()
    val pics = images()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10))
            .background(Color(colors.random()))
            .clickable { onclick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 10.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = pics.random()),
                contentDescription = "",
                modifier = Modifier.size(45.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = time, fontWeight = FontWeight.Light, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.weight(0.1f))
        IconButton(onClick = { onclick() }) {
            Image(
                painter = painterResource(id = R.drawable.play_icon_white),
                contentDescription = "",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}