package com.akichiy.rickandmortyapp.domain.usecase

import com.akichiy.rickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.akichiy.rickandmortyapp.domain.model.Character


class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page: Int): Flow<Result<List<Character>>> {
        return repository.getAllCharacters(page)
    }
}