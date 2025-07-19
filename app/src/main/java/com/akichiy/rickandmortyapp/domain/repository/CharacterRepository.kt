package com.akichiy.rickandmortyapp.domain.repository

import com.akichiy.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(page: Int): Flow<Result<List<Character>>>

    fun getCharacterById(id: Int): Flow<Result<Character>>

    fun filterCharacters(
        name: String? = null,
        status: String? = null,
        gender: String? = null,
        species: String? = null
    ): Flow<Result<List<Character>>>

}