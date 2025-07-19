package com.akichiy.rickandmortyapp.presentation.character_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akichiy.rickandmortyapp.presentation.character_list_screen.components.CharacterCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pullRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    SwipeRefresh(
        state = pullRefreshState,
        onRefresh = { viewModel.refresh() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.background
            )
        }
        Column {
            TopAppBar(title = { Text("Персонажи") })
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = androidx.compose.ui.Modifier
                        .align(Alignment.CenterHorizontally)

                )
            }
            if (state.error != null) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "Ошибка: ${state.error}"
                )
            }
            if (state.characters.isEmpty() && !state.isLoading) {
                Text(
                    "Ничего не найдено", Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
            val listState = rememberLazyGridState()

            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                itemsIndexed(
                    items = state.characters,
                    key = { _, character -> character.id }
                ) { index, character ->
                    CharacterCard(character) {
                        navController.navigate("character_detail/${character.id}")
                    }
                }

                if (state.isLoadingNextPage) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            LaunchedEffect(listState) {
                snapshotFlow {
                    val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val totalItems = listState.layoutInfo.totalItemsCount
                    lastVisible >= totalItems - 4 // подгружать за 4 элемента до конца
                }.distinctUntilChanged()
                    .filter { it }
                    .collect {
                        viewModel.loadCharacters()
                    }
            }
            //////////////////////NO PAGINATION////////////////////////////////

//            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
//                itemsIndexed(
//                    items = state.characters,
//                    key = { _, character -> character.id}
//                ) { index, character ->
//                    CharacterCard(character) {
//                        navController.navigate("character_detail/${character.id}")
//                    }
//                }
//            }
        }
    }
}