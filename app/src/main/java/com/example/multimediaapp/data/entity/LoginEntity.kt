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


/*nota:
    Al coincidir el campo del JSON falta poner val

 */
/*
 * Este archivo define la clase UsersEntity, que representa los datos de usuario
 * tal como se reciben desde la API en formato JSON.
 *
 * Pertenece a la capa de datos (Data Layer) y se utiliza únicamente para mapear
 * la respuesta del servidor a objetos Kotlin.
 *
 * La anotación @SerializedName se usa para indicar cómo se llaman los campos en el JSON
 * y así poder mapearlos correctamente a las propiedades de la clase.
 *
 * En este caso:
 * - "email" del JSON se asigna a la propiedad email
 * - "pass" del JSON se asigna a la propiedad pass
 *
 * Esta clase no contiene lógica de negocio, solo representa la estructura de los datos.
 *
 * Además, se incluye una función de mapeo (toDTO) que convierte un objeto UsersEntity
 * en un LoginDTO, que es el modelo utilizado dentro de la aplicación.
 *
 * Este proceso permite separar:
 * - Los datos de la API (Entity)
 * - Los datos que usa la app (DTO)
 *
 * De esta forma se mantiene una arquitectura más limpia y desacoplada.
 */