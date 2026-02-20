package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.DialogUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DialogVM : ViewModel(){
    private val _uiState = MutableStateFlow(DialogUiState())
    val uiState: StateFlow<DialogUiState> = _uiState.asStateFlow()

    fun showDialog() {
        _uiState.value = _uiState.value.copy(isVisible = true)
    }

    fun hideDialog() {
        _uiState.value = _uiState.value.copy(isVisible = false)
    }

    fun confirmAction() {
        _uiState.value = _uiState.value.copy(
            isConfirmed = true,
            isVisible = false
        )
    }

}