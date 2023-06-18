package com.example.recipebook.CustomComposes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.example.recipebook.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    holderValue: String? = null,
    value1: String = "",
    size: Int = 20,
    enabled: Boolean = true,
    onDone: (String) -> Unit
) {
    var value by remember { mutableStateOf(value1) }
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            value = it
            onDone(it)
                        },
        enabled = enabled,
        textStyle = TextStyle(
            fontSize = size.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.quicksand))
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent
        ),
        placeholder = {
            holderValue?.let {
                Text(text = it, fontSize = size.sp, fontWeight = FontWeight.Bold)
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}