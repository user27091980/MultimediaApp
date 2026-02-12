package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onDarkModeChange(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkMode = enabled)
    }
}