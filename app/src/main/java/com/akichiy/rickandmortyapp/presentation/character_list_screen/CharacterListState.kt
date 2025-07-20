package com.akichiy.rickandmortyapp.presentation.character_list_screen
import com.akichiy.rickandmortyapp.domain.model.Character

data class CharacterListState(

    val characters: List<Character> = emptyList(),
    val filteredCharacters: List<Character>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null,
    val currentPage: Int = 1,
    val isLoadingNextPage: Boolean = false,
    val endReached: Boolean = false
)