package com.example.recipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.Pages.ViewRecipe
import com.example.recipebook.Pages.MainPage
import com.example.recipebook.data.MainViewModel
import com.example.recipebook.ui.theme.RecipeBookTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainViewModel>()

                    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
                    val skipPartiallyExpanded by remember { mutableStateOf(false) }
                    val scope = rememberCoroutineScope()
                    val bottomSheetState = rememberModalBottomSheetState(
                        skipPartiallyExpanded = skipPartiallyExpanded
                    )
                    Column(Modifier.fillMaxSize()) {
                        MainPage(viewModel){
                            viewModel.viewRecipe(it)
                            scope.launch { bottomSheetState.expand() }.invokeOnCompletion {
                                if (bottomSheetState.isVisible) {
                                    openBottomSheet = true
                                }
                            }
                        }
                        if(openBottomSheet){
                            ModalBottomSheet(
                                onDismissRequest = {
                                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                        if (!bottomSheetState.isVisible) {
                                            openBottomSheet = false
                                        }
                                    }
                                },
                                sheetState = bottomSheetState
                            ) {
                                ViewRecipe(viewModel.viewRecipe.value!!) {
                                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                        if (!bottomSheetState.isVisible) {
                                            openBottomSheet = false
                                        }
                                    }
                                    when(it){
                                        "Edit" -> {
                                            viewModel.setNewRecipe()
                                            viewModel.expandAddScreen()
                                        }
                                        "Delete" -> {
                                            viewModel.deleteRecipe(viewModel.viewRecipe.value!!.id)
                                        }
                                        else -> { }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}