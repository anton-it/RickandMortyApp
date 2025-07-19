package com.akichiy.rickandmortyapp.presentation.character_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akichiy.rickandmortyapp.presentation.character_detail_screen.components.DetailsCharacterCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(characterId: Int, navController: NavController, viewModel: CharacterDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = characterId) {
        viewModel.loadCharacter(characterId)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Детали") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            }
        )
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            state.character?.let { character ->
                DetailsCharacterCard(character)

            }
//            state.character?.let { char ->
//                AsyncImage(model = char.image, contentDescription = char.name)
//                Text("Имя: ${char.name}")
//                Text("Статус: ${char.status}")
//                Text("Вид: ${char.species}")
//                Text("Пол: ${char.gender}")
//                Text("Происхождение: ${char.origin}")
//                Text("Локация: ${char.location}")
//                Text("Кол-во эпизодов: ${char.episodeCount}")
//            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.error?.let {
                Text("Ошибка: $it")
            }
        }
    }
}