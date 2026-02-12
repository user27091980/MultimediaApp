package com.example.multimediaapp.data.model

//usar val para evitar mutabilidad
data class UsersDTO(
    val id: Int,
    val email: String,
    val user: String,
    val pass: String
)
