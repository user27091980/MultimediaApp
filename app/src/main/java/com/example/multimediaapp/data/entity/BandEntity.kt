package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.BandDTO
import com.google.gson.annotations.SerializedName


/*
Representa un modelo de datos simple.
Kotlin generará automáticamente equals(), hashCode(), toString(), copy().
Ideal para JSON, RecyclerView, Room, etc.
 */

data class BandEntity(

    val id: String,
    @SerializedName("nombre") val name: String,
    @SerializedName("texto") val textInfo: String,
    @SerializedName("cabecera") val headerImage: String,
    @SerializedName ("estilo") val style: String,
    @SerializedName ("discográfica") val recordLabel: String,
    @SerializedName ("componentes") val components: String,
    @SerializedName ("discografía") val discography: List<String>,
    @SerializedName("discos") val albumImages: List<String>,
    @SerializedName("imagen") val imageBand: String

)
//mapper para convertir en BanDTO
fun BandEntity.toDTO(): BandDTO = BandDTO(
    id = id,
    name = name,
    textInfo = textInfo,
    headerImage = headerImage,
    albumImages = albumImages,
    style = style,
    recordLabel = recordLabel,
    components = components,
    discography = discography,
    imageBand=imageBand
)
