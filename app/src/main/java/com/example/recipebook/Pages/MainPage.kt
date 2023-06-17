package com.example.recipebook.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.example.recipebook.CustomComposes.RecipeUi
import com.example.recipebook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    Onclick:() -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val isAddOpen = remember { mutableStateOf(false) }

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
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    items(16){
                        RecipeUi(
                            img = painterResource(id = R.drawable.add_icon_white),
                            name = "Masala porotta",
                            time = "45min",
                            onclick = { Onclick() }
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
                SearchPage(onSearch = {})
            }
        }
    }
}