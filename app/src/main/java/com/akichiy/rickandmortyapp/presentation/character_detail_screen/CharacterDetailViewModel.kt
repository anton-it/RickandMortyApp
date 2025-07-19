package com.akichiy.rickandmortyapp.presentation.character_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akichiy.rickandmortyapp.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.akichiy.rickandmortyapp.domain.model.Character

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterById(id).collect { result ->
                result.onSuccess {
                    _state.value = CharacterDetailState(character = it)
                }.onFailure {
                    _state.value = CharacterDetailState(error = it.message)
                }
            }
        }
    }
}

data class CharacterDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)