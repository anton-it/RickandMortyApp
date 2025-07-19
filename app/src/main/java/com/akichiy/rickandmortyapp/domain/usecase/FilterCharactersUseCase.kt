package com.akichiy.rickandmortyapp.domain.usecase

import com.akichiy.rickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.akichiy.rickandmortyapp.domain.model.Character

class FilterCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        name: String? = null,
        status: String? = null,
        gender: String? = null,
        species: String? = null
    ): Flow<Result<List<Character>>> {
        return repository.filterCharacters(name, status, gender, species)
    }
}