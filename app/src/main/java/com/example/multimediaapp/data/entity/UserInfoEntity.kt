package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UsersInfoDTO
import com.google.gson.annotations.SerializedName

//para comentario ver BandEntity
data class UsersInfoEntity (
    val id: String,
    @SerializedName("email") val email:String,
    @SerializedName("usuario") val user: String,
    @SerializedName("pais") val country: String,
    @SerializedName("nombre") val name: String,
    @SerializedName("apellidos") val surname: String,
)

//mapper para convertir en UsersDTO
fun UsersInfoEntity.toDTO(): UsersInfoDTO = UsersInfoDTO(
    id = id,
    email=email,
    user = user,
    country=country,
    name=name,
    surname=surname

)