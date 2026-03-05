package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.MainDTO
import com.google.gson.annotations.SerializedName

data class MainEntity(

    val id: String,

    // Imagen principal de la banda
    @SerializedName("imagenBanda") val imageBand: String
)
    //mapper para convertir en UsersDTO
fun MainEntity.toDTO(): MainDTO = MainDTO(
        id = id,
        imageBand =imageBand

)