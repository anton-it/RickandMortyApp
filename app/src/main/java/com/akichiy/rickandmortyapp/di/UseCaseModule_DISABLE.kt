package com.akichiy.rickandmortyapp.di

import com.akichiy.rickandmortyapp.domain.repository.CharacterRepository
import com.akichiy.rickandmortyapp.domain.usecase.FilterCharactersUseCase
import com.akichiy.rickandmortyapp.domain.usecase.GetCharacterByIdUseCase
import com.akichiy.rickandmortyapp.domain.usecase.GetCharactersUseCase

//@Module
//@InstallIn(SingletonComponent::class)
object UseCaseModule_DISABLE {

//    @Provides
//    @Singleton
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase = GetCharactersUseCase(repository)

//    @Provides
//    @Singleton
    fun provideGetCharacterByIdUseCase(
        repository: CharacterRepository
    ): GetCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

//    @Provides
//    @Singleton
    fun provideFilterCharactersUseCase(
        repository: CharacterRepository
    ): FilterCharactersUseCase = FilterCharactersUseCase(repository)
}