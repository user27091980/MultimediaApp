package com.example.multimediaapp.data.entity

import com.google.gson.annotations.SerializedName

data class AlbumsEntity(
    val id: Int,
    @SerializedName("discos") val albums: List<String>
)