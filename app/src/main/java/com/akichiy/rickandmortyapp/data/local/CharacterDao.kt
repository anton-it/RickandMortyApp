package com.akichiy.rickandmortyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()

    @Query("SELECT * FROM characters WHERE " +
            "(:name IS NULL OR name LIKE '%' || :name || '%') AND " +
            "(:status IS NULL OR status = :status) AND " +
            "(:gender IS NULL OR gender = :gender) AND " +
            "(:species IS NULL OR species = :species)")

    fun filterCharacters(
        name: String?,
        status: String?,
        gender: String?,
        species: String?
    ): Flow<List<CharacterEntity>>
}