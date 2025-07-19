package com.akichiy.rickandmortyapp.domain.usecase

import com.akichiy.rickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.akichiy.rickandmortyapp.domain.model.Character

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Result<Character>> {
        return repository.getCharacterById(id)
    }
}