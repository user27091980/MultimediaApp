package com.example.multimediaapp.viewmodel.uistate

/**
 * UI State para LoginRegScreen
 *
 * Representa el estado de la pantalla de login/registro.
 *
 * Autor: Andrés
 */
data class LoginRegUiState(
    val isLoginEnabled: Boolean = true,      // Si el botón login está activo
    val isRegisterEnabled: Boolean = true,   // Si el botón registro está activo
    val isLoading: Boolean = false,          // Para mostrar indicador de carga
    val errorMessage: String? = null         // Mensajes de error
)