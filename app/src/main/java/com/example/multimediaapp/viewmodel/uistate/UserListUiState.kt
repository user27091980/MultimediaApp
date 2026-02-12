package com.example.multimediaapp.viewmodel.uistate

data class UserListUiState(val bands: List<UserUIState> = ArrayList())

data class UserUIState(
    val id: String,
    val user: String,
    val pass: String

)
