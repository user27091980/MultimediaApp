package com.example.multimediaapp.viewmodel.uistate

/**
 * UI State para LoginRegScreen
 *
 * Representa el estado de la pantalla de login/registro.
 *
 * Autor: Andrés
 */
data class LoginRegUiState(
    // Si el botón login está activo
    val isLoginEnabled: Boolean = true,
    // Si el botón registro está activo
    val isRegisterEnabled: Boolean = true,
    // Para mostrar indicador de carga
    val isLoading: Boolean = false,
    // Mensajes de error
    val errorMessage: String? = null
)