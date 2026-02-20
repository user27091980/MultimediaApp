package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UsersDTO
import com.google.gson.annotations.SerializedName

data class UsersEntity (
    val id: String,
    @SerializedName("email") val email:String,
    @SerializedName("usuario") val user: String,
    @SerializedName("pass") val pass: String,
)

//mapper para convertir en UsersDTO
fun UsersEntity.toDTO(): UsersDTO = UsersDTO(
    id = id,
    email=email,
    user = user,
    pass=pass

)