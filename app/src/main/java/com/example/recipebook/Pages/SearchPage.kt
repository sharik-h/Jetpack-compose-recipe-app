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
import com.example.recipebook.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    search: List<Recipe>? = null,
    onSearch: (String) -> Unit,
    onSelect: (String) -> Unit
) {

    val srch = remember { mutableStateOf("") }
    val searchHistory = remember { mutableListOf<Pair<String, String>>() }
    var state by remember { mutableStateOf(false) }

    Bubble(
        activeContent = {
            SearchBar(
                query = srch.value,
                onQueryChange = {
                    srch.value = it
                    onSearch(it)
                },
                active = true,
                onActiveChange = {  },
                placeholder = { Text(text = "Search") },
                onSearch = { onSearch(it) },
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
                if (srch.value.isEmpty()){
                    searchHistory.reversed().forEach { history ->
                        HistoryItem(
                            value = history.second,
                            onClick = {
                                state = false
                                onSelect(history.first)
                                srch.value = ""
                            },
                            onSelect = { srch.value = history.second }
                        )
                    }
                }else{
                    search?.forEach {
                        SearchItem(value = it.name) {
                            state = false
                            if(!searchHistory.contains(Pair(it.id, it.name))) searchHistory.add(Pair(it.id,it.name))
                            srch.value = ""
                            onSelect(it.id)
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