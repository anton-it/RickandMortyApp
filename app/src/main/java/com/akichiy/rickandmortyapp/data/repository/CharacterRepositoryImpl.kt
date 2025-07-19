package com.akichiy.rickandmortyapp.data.repository

import com.akichiy.rickandmortyapp.data.local.CharacterDao
import com.akichiy.rickandmortyapp.data.remote.CharacterApi
import com.akichiy.rickandmortyapp.data.utils.toDomain
import com.akichiy.rickandmortyapp.data.utils.toEntity
import com.akichiy.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject
import com.akichiy.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi,
    private val dao: CharacterDao
) : CharacterRepository {

    override fun getAllCharacters(page: Int): Flow<Result<List<Character>>> = flow {
        try {
            val response = api.getAllCharacters(page)
            val entities = response.results.map { it.toEntity() }
            dao.insertAll(entities)
        } catch (_: Exception) { /* fallback */ }

        emitAll(
            dao.getAllCharacters().map { Result.success(it.map { it.toDomain() }) }
        )
    }.catch { emit(Result.failure(it)) }

    override fun getCharacterById(id: Int): Flow<Result<Character>> = flow {
        emitAll(
            dao.getCharacterById(id)
                .map { Result.success(it.toDomain()) }
        )
    }.catch { emit(Result.failure(it)) }

    override fun filterCharacters(
        name: String?,
        status: String?,
        gender: String?,
        species: String?
    ): Flow<Result<List<Character>>> = flow {
        try {
            val response = api.filterCharacters(name, status, gender, species)
            dao.insertAll(response.results.map { it.toEntity() })
        } catch (_: Exception) { /* fallback */ }

        emitAll(
            dao.filterCharacters(name, status, gender, species)
                .map { Result.success(it.map { it.toDomain() }) }
        )
    }.catch { emit(Result.failure(it)) }
}