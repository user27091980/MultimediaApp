package com.example.multimediaapp.viewmodel.uistate

// A data class to hold all the Login state for the screen, pass visibility, ...
data class LoginUiState(
    val user: String="",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading
}