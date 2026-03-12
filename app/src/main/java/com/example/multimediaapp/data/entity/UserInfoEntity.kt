package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UsersInfoDTO
import com.google.gson.annotations.SerializedName

//para comentario ver BandEntity
data class UsersInfoEntity(
    val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("user") val user: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
)

//mapper para convertir en LoginDTO
fun UsersInfoEntity.toDTO(): UsersInfoDTO = UsersInfoDTO(
    id = id,
    email = email,
    user = user,
    name = name,
    surname = surname

)