package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DialogVM : ViewModel() {

    private val _uiState = MutableStateFlow(false) // false = no mostrar
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    fun showDialog() { _uiState.value = true }
    fun hideDialog() { _uiState.value = false }
    fun confirmAction() { _uiState.value = false }
}
