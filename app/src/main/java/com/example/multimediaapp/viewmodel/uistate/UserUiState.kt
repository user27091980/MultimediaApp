package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.UserDTO

data class UserUiState(
    val email: String = "",
    val pass: String = "",
    val name: String = "",
    val lastName: String = "",
    val country: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val user: UserDTO? = null
)