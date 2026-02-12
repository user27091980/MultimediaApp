package com.example.multimediaapp.viewmodel.uistate

data class UserListUIState(val bands: List<UserUIState> = ArrayList())

data class UserUIState(
    val id: Int,
    val user: String,
    val pass: String

)
