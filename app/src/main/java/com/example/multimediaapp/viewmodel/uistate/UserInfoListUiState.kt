package com.example.multimediaapp.viewmodel.uistate

data class UserInfoListUiState(val userInfo: List<UserInfoUiState> = ArrayList())

data class UserInfoUiState(
    val id: String,
    val email: String,
    val user: String,
    val country: String,
    val name: String,
    val surname: String
)



