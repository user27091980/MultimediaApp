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
/*
 * Este archivo define la clase UsersInfoEntity, que representa la información completa
 * del usuario tal como se recibe desde la API.
 *
 * Pertenece a la capa de datos (Data Layer) y su función es mapear la respuesta del servidor
 * a un objeto Kotlin que posteriormente será transformado y usado dentro de la aplicación.
 *
 * Los campos incluyen información personal del usuario como:
 * - email
 * - contraseña (pass)
 * - nombre
 * - apellidos
 * - país
 *
 * La anotación @SerializedName se utiliza para mapear los nombres del JSON con las propiedades
 * de la clase cuando no coinciden exactamente.
 *
 * Esta clase no contiene lógica de negocio, únicamente estructura de datos.
 *
 * Se incluyen dos funciones de mapeo:
 *
 * 1. toDTO():
 *    Convierte UsersInfoEntity en UsersInfoDTO.
 *    - Se usa para transformar los datos de la API en un modelo utilizado por la app.
 *    - Permite trabajar con un modelo independiente de la fuente de datos.
 *
 * 2. toEntity():
 *    Convierte UsersInfoDTO en UsersInfoEntity.
 *    - Se utiliza cuando se necesita enviar datos al servidor o guardarlos.
 *
 * Este sistema de mapeo permite separar claramente:
 * - La capa de datos (Entity)
 * - La capa de la aplicación (DTO)
 *
 * Con ello se consigue una arquitectura más limpia, organizada y fácil de mantener.
 */