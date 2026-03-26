package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UsersInfoDTO
import com.google.gson.annotations.SerializedName
import kotlin.String

/**
 * UsersInfoEntity
 *
 * Representa la información del usuario tal como llega desde la API.
 * Pertenece a la capa de datos y se convierte en UsersInfoDTO
 * para ser utilizada dentro de la aplicación.
 */
data class UsersInfoEntity(
    val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("pass") val pass: String,
    @SerializedName("name")val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("country") val country: String
)

/**
 * Mapper: UsersInfoEntity a UsersInfoDTO
 * Convierte la entidad de datos en un modelo usado por la aplicación.
 */
fun UsersInfoEntity.toDTO() = UsersInfoDTO(
    id=this.id,
    email = this.email,
    pass = this.pass,
    name = this.name,
    lastName = this.lastName,
    country = this.country
)

/**
 * Mapper inverso: UsersInfoDTO a UsersInfoEntity
 * Permite convertir el modelo de la app en entidad
 * para persistencia o envío a la API.
 */
fun UsersInfoDTO.toEntity() = UsersInfoEntity(
    id=this.id,
    email = this.email,
    pass = this.pass,
    name = this.name,
    lastName = this.lastName,
    country = this.country
)