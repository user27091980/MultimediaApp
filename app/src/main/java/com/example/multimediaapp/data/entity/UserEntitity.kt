package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.UserDTO
import com.google.gson.annotations.SerializedName

/**
 * UserEntity
 *
 * Representa los datos tal como llegan desde la API.
 */
data class UserEntity(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("email")
    val email: String,

    @SerializedName("pass")
    val pass: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("lastName")
    val lastName: String? = null,

    @SerializedName("country")
    val country: String? = null
)
fun UserEntity.toDTO() = UserDTO(
    id = id,
    email = email,
    pass = pass,
    name = name,
    lastName = lastName,
    country = country
)