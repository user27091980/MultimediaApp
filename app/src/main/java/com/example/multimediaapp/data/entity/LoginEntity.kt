package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.LoginDTO
import com.google.gson.annotations.SerializedName

//para comentario ver BandEntity
data class UsersEntity(
    val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("user") val user: String,
    @SerializedName("pass") val pass: String,
)

//mapper para convertir en LoginDTO
fun UsersEntity.toDTO(): LoginDTO = LoginDTO(
    id = id,
    email = email,
    user = user,
    pass = pass

)