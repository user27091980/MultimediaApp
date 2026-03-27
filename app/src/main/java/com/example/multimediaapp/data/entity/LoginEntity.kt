package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.LoginDTO
import com.google.gson.annotations.SerializedName

/**
 * LoginEntity
 *
 * Representa los datos de usuario tal como llegan desde la API.
 * Pertenece a la capa de datos (Data Layer) y se convierte
 * a LoginDTO para ser usado en la aplicación.
 */
data class LoginEntity(
    val id: String,
    // Campos recibidos desde el JSON de la API
    @SerializedName("user") val user: String,

    @SerializedName("passwd") val pass: String
)
/*

* LoginEntity
*
* Esta clase representa los datos de usuario tal como llegan desde la API.
* Pertenece a la capa de datos y se utiliza para mapear la respuesta JSON.
*
* Incluye los campos básicos como id, email y contraseña (pass),
* usando @SerializedName para asegurar la correcta correspondencia con el JSON.
*
* Se incluye una función de conversión (toDTO) que transforma el LoginEntity
* en un LoginDTO, que es el modelo utilizado en la lógica de la aplicación.
*
* Este mapeo permite separar la capa de datos de la capa de dominio,
* facilitando el mantenimiento y la organización del código.
*
* Nota: si el nombre de la propiedad coincide con el del JSON,
* la anotación @SerializedName no es estrictamente necesaria.
  */

//mapper para convertir en LoginDTO
fun LoginDTO.toEntity(): LoginEntity {
    return LoginEntity(
        id = id,
        user = user,
        pass=pass
    )
}
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