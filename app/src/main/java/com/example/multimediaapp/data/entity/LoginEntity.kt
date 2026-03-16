package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.LoginDTO
import com.google.gson.annotations.SerializedName

/**
 * UsersEntity
 *
 * Representa los datos de usuario tal como llegan desde la API.
 * Pertenece a la capa de datos (Data Layer) y se convierte
 * a LoginDTO para ser usado en la aplicación.
 */
data class UsersEntity(
    val id: String,
    // Campos recibidos desde el JSON de la API
    @SerializedName("email") val email: String,
    @SerializedName("user") val user: String,
    @SerializedName("pass") val pass: String
)

//mapper para convertir en LoginDTO
fun UsersEntity.toDTO(): LoginDTO = LoginDTO(
    id = id,
    email = email,
    user = user,
    pass = pass

)


/*nota:
    Al coincidir el campo del JSON falta poner val

 */