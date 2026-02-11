package com.example.multimediaapp.data.entity

import com.google.gson.annotations.SerializedName

data class UsersEntity (
    val id: Int,
    @SerializedName("usuario") val user: List<String>,
    @SerializedName("pass") val pass: String,
)
