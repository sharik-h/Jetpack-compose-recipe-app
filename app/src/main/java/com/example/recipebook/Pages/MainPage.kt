package com.example.recipebook.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.CustomComposes.RecipeUi
import com.example.recipebook.R
import com.example.recipebook.data.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: MainViewModel,
    Onclick:(String) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val isAddOpen = remember { mutableStateOf(false) }
    val recipies = viewModel.recipies.value
    val newRecipe = viewModel.newReicpe.value

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Scaffold(
                topBar = {
                    Box {
                        LargeTopAppBar(
                            title = { Text(text = "Recipe", color = Color.Black) },
                            scrollBehavior = scrollBehavior,
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                        )
                    }
                }
            ) { paddingValue ->
                if (recipies != null) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        state = lazyListState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValue)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    ) {
                        items(items = recipies){
                            RecipeUi(
                                img = painterResource(id = it.img.toInt()),
                                name = it.name,
                                time = it.time,
                                onclick = { Onclick(it.id) }
                            )
                        }
                    }
                }
                else if( !viewModel.exceptoins.value.isNullOrEmpty()){
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.broken_cable),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = viewModel.exceptoins.value,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }
                } else{
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Please wait,\n we are getting your recipes from server",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            AddRecipe(
                recipe = {},
                top = { isAddOpen.value = it }
            )
        }
        if (!isAddOpen.value){
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxSize()
            ) {
                SearchPage(
                    search = viewModel.searchResult.value,
                    onSearch = { viewModel.searchRecipe(it) },
                    onSelect = { Onclick(it) }
                )
            }
        }
    }
}