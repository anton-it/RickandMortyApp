package com.akichiy.rickandmortyapp.data.remote

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>
)

data class LocationDto(
    val name: String
)
