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
    @SerializedName("passwd") val passwd: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("country") val country: String
)

/**
 * Mapper: UsersInfoEntity a UsersInfoDTO
 * Convierte la entidad de datos en un modelo usado por la aplicación.
 */
fun UsersInfoEntity.toDTO() = UsersInfoDTO(
    id =this.id,

    email = this.email,
    passwd = this.passwd,
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
    id =this.id,
    email = this.email,
    passwd = this.passwd,
    name = this.name,
    lastName = this.lastName,
    country = this.country
)


/*

* UsersInfoEntity
*
* Esta clase representa la información completa del usuario tal como llega desde la API.
* Pertenece a la capa de datos y se utiliza para mapear la respuesta JSON.
*
* Contiene campos como id, email, contraseña, nombre, apellidos y país,
* utilizando @SerializedName para asegurar la correspondencia con el JSON.
*
* Incluye funciones de conversión (mappers):
*
* * toDTO():
* Convierte UsersInfoEntity en UsersInfoDTO, que es el modelo utilizado
* en la lógica de la aplicación.
*
* * toEntity():
* Convierte UsersInfoDTO en UsersInfoEntity.
* Se utiliza cuando se necesita enviar datos al servidor o almacenarlos
* en el formato de la API.
*
* Este enfoque permite separar la capa de datos de la lógica de la aplicación,
* facilitando el mantenimiento, la organización y la escalabilidad del código.
*
* Nota: aunque se incluye el campo passwd, no es recomendable almacenar
* contraseñas en texto plano por motivos de seguridad.
  */
