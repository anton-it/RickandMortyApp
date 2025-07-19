package com.akichiy.rickandmortyapp.presentation.character_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akichiy.rickandmortyapp.domain.usecase.FilterCharactersUseCase
import com.akichiy.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val filterCharactersUseCase: FilterCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters(page: Int = state.value.currentPage) {
        if (state.value.isLoadingNextPage || state.value.endReached) return

        viewModelScope.launch {
            _state.update { it.copy(isLoadingNextPage = true) }

            getCharactersUseCase(page).collect { result ->
                result.onSuccess { newCharacters ->
                    val isEnd = newCharacters.isEmpty()
                    _state.update {
                        it.copy(
                            characters = it.characters + newCharacters,
                            currentPage = page + 1,
                            isLoadingNextPage = false,
                            endReached = isEnd
                        )
                    }
                }.onFailure {
                    _state.update {
                        it.copy(isLoadingNextPage = false, error = it.message ?: "Ошибка загрузки")
                    }
                }
            }
        }
        }


////////NO PAGINATION
//    fun loadCharacters(page: Int = 1) {
//        viewModelScope.launch {
//            getCharactersUseCase(page).collect { result ->
//                result.onSuccess {
//                    _state.update { listState ->
//                        listState.copy(
//                            characters = listState.characters + it,
//                            isLoading = false
//                        )
//                    }
//                }.onFailure {
//                    _state.update {
//                        it.copy(error = it.message ?: "Ошибка")
//                    }
//                }
//            }
//        }
//    }

        fun applyFilter(name: String?, status: String?, gender: String?, species: String?) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                filterCharactersUseCase(name, status, gender, species).collect { result ->
                    result.onSuccess {
                        _state.update { listState ->
                            listState.copy(
                                characters = it,
                                isLoading = false,
                                error = null
                            )
                        }
                    }.onFailure {
                        _state.update {
                            it.copy(error = it.message ?: "Ошибка")
                        }
                    }
                }
            }
        }

        fun refresh() {
            _state.update { it.copy(characters = emptyList()) }
            loadCharacters()
        }
    }
