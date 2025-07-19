package com.akichiy.rickandmortyapp.data.remote


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): ApiResponseDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto

    @GET("character")
    suspend fun filterCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("gender") gender: String?,
        @Query("species") species: String?
    ): ApiResponseDto
}