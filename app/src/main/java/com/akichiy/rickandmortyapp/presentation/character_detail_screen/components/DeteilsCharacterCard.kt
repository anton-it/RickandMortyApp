package com.akichiy.rickandmortyapp.presentation.character_detail_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.akichiy.rickandmortyapp.domain.model.Character

@Composable
fun DetailsCharacterCard(
    character: Character,
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Имя: ${character.name}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Статус: ${character.status}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Вид: ${character.species}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Пол: ${character.gender}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Происхождение: ${character.origin}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Локация: ${character.location}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Кол-во эпизодов: ${character.episodeCount}",
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}