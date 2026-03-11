package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainVM : ViewModel() {

    private val repo = MainRepoRetrofit()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    fun loadData() {
        viewModelScope.launch {
            try {
                val bands = repo.getBands()
                _uiState.value = _uiState.value.copy(mainBands = bands)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(mainBands = emptyList())
            }
        }
    }
}