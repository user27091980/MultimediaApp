package com.example.multimediaapp.viewmodel.uistate

data class RegisterListUiState(val regUser: List<RegisterUIState> = ArrayList())

data class RegisterUIState(
    val id: String,
    val user: String,
    val pass: String

)