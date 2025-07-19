package com.akichiy.rickandmortyapp.data.utils

import com.akichiy.rickandmortyapp.data.local.CharacterEntity
import com.akichiy.rickandmortyapp.data.remote.CharacterDto
import com.akichiy.rickandmortyapp.domain.model.Character

fun CharacterDto.toEntity(): CharacterEntity = CharacterEntity(
    id, name, status, species, type, gender,
    origin.name, location.name, image, episode.size
)

fun CharacterEntity.toDomain(): Character =
    Character(
        id, name, status, species, type, gender,
        origin, location, image, episodeCount
    )