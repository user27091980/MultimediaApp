package com.example.multimediaapp.model

//usar val para evitar mutabilidad
data class UsersDTO(
    val id: String,
    val email: String,
    val user: String,
    val pass: String
)
