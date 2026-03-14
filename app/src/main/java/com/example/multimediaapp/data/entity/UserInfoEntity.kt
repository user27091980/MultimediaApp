package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UsersInfoDTO
import com.google.gson.annotations.SerializedName
import kotlin.String

/**
 * Representación de la información del usuario en la capa de datos.
 * Esta clase se encarga de la persistencia (Base de datos).
 */
data class UsersInfoEntity(
    val email: String,
    val pass: String,
    val user: String,
    val name: String,
    val lastName: String,
    val country: String
)

/**
 * Función de extensión para convertir una Entity de la base de datos
 * en un DTO para la UI o la API.
 */
fun UsersInfoEntity.toDTO() = UsersInfoDTO(
    email = this.email,
    pass = this.pass,
    user = this.user,
    name = this.name,
    lastName = this.lastName,
    country = this.country
)

/**
 * Función de extensión para convertir un DTO en una Entity
 * para poder guardarlo en la base de datos.
 */
fun UsersInfoDTO.toEntity() = UsersInfoEntity(
    email = this.email,
    pass = this.pass,
    user = this.user,
    name = this.name,
    lastName = this.lastName,
    country = this.country
)