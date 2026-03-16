package com.example.multimediaapp.viewmodel.uistate

/*
 * Estado de la pantalla de registro de usuario para el formulario.
 *
 * Contiene los valores actuales de los campos y un posible mensaje de error.
 */
data class RegisterFormUiState(
    val user: String = "",
    val email: String = "",
    val pass: String = "",
    val name: String = "",
    val country: String = "",
    val errorMessage: String? = null,
    val lastName: String,
    val isLoading: Boolean
)
