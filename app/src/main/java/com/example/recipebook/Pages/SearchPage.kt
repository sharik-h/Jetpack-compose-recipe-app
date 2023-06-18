package com.example.recipebook.Pages

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.recipebook.R
import androidx.compose.material3.SearchBar
import androidx.compose.ui.graphics.Color
import com.example.recipebook.CustomComposes.Bubble
import com.example.recipebook.CustomComposes.HistoryItem
import com.example.recipebook.CustomComposes.SearchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    search: Map<String, String>? = null,
    onSearch: (String) -> Unit,
    onSelect: (String) -> Unit
) {

    val srch = remember { mutableStateOf("") }
    val searchHistory = remember { mutableListOf<String>() }
    var state by remember { mutableStateOf(false) }

    Bubble(
        activeContent = {
            SearchBar(
                query = srch.value,
                onQueryChange = { srch.value = it },
                active = true,
                onActiveChange = {  },
                placeholder = { Text(text = "Search") },
                onSearch = {
                    onSearch(it)
                    searchHistory.add(it)
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.search_icon_gray), contentDescription = "")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (srch.value == "") {
                            state = false
                        }else {
                            srch.value = ""
                        }
                    } )
                    {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "")
                    }
                }
            ) {
                if (srch.value == ""){
                    searchHistory.reversed().forEach { history ->
                        HistoryItem(value = history) { srch.value = history }
                    }
                }else{
                    search?.forEach {
                        SearchItem(value = it.value) {
                            onSelect(it.key)
                            state = false
                        }
                    }
                }
            }
        },
        inactiveContent = {
            IconButton(onClick = { state = true }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        },
        states = state
    )
}