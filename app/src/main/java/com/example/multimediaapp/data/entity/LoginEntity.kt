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

    @SerializedName("pass") val pass: String
)

//mapper para convertir en LoginDTO
fun UsersEntity.toDTO(): LoginDTO = LoginDTO(
    id = id,
    email = email,
    pass = pass

)
/*

* UsersEntity
*
* Esta clase representa los datos de usuario tal como llegan desde la API.
* Pertenece a la capa de datos y se utiliza para mapear la respuesta JSON.
*
* Incluye los campos básicos como id, email y contraseña (pass),
* usando @SerializedName para asegurar la correcta correspondencia con el JSON.
*
* Se incluye una función de conversión (toDTO) que transforma el UsersEntity
* en un LoginDTO, que es el modelo utilizado en la lógica de la aplicación.
*
* Este mapeo permite separar la capa de datos de la capa de dominio,
* facilitando el mantenimiento y la organización del código.
*
* Nota: si el nombre de la propiedad coincide con el del JSON,
* la anotación @SerializedName no es estrictamente necesaria.
  */


/*nota:
    Al coincidir el campo del JSON falta poner val

 */