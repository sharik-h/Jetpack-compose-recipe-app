package com.example.recipebook.Pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.recipebook.CustomComposes.Bubble
import com.example.recipebook.data.MainViewModel
import com.example.recipebook.model.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AddRecipe(
    viewModel: MainViewModel,
    newRecipe: Recipe?,
    top: (Boolean) -> Unit,
    recipe:(Recipe) -> Unit
) {
    var state by remember { mutableStateOf(false) }
    var confirmState by remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current

    Bubble(
        activeContent = {
            AddPage(
                viewModel = viewModel,
                newRecipe = newRecipe
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {
                        if (viewModel.validateInput()){
                            viewModel.createRecipe()
                            viewModel.clearData()
                            top(false)
                            state = false
                        }else{
                            if(viewModel.nothingInputed() || confirmState){
                                top(false)
                                state = false
                                viewModel.clearData()
                                confirmState = false
                            }else{
                                confirmState = true
                                Toast.makeText(
                                    context,
                                    "Press Again to Exit. Caution: All the Data you have entered will be lost.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                coroutine.launch {
                                    delay(3000)
                                    confirmState = false
                                }
                            }
                        }
                    },
                ) {
                    Icon(
                        imageVector = if (viewModel.validateInput()){
                            println(viewModel.validateInput())
                            Icons.Default.Done
                        }else {
                            Icons.Default.Close
                              },
                        contentDescription = ""
                    )
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