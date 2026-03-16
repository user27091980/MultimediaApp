package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.BandDTO
import com.google.gson.annotations.SerializedName

/**
 * BandEntity representa cómo llegan los datos de una banda desde la API.
 * Vive en la capa de datos (Data Layer) y se convierte a BandDTO para la app.
 */
data class BandEntity(
    val id: String,

    //Los nombres en @SerializedName deben coincidir con las llaves del JSON de tu API
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("banner") val banner: String,
    @SerializedName("albumImages") val albumImages: List<String>,
    @SerializedName("style") val style: String,
    @SerializedName("recordLabel") val recordLabel: String,
    @SerializedName("components") val components: String,
    @SerializedName("discography") val discography: List<String>,
    @SerializedName("albumLinks") val albumLinks: List<String>,
    @SerializedName("headerLink") val headerLink: String
)

/**
 * Mapper de BandEntity a BandDTO
 */
fun BandEntity.toDTO(): BandDTO {

    val baseUrl = "http://10.0.2.2/"

    return BandDTO(
        id = id,
        name = name,
        description = description,
        // Limpiamos barras iniciales con removePrefix para evitar "//"
        banner = if (banner.startsWith("http")) banner else baseUrl + banner.removePrefix("/"),
        albumImages = albumImages.map { url ->
            if (url.startsWith("http")) url else baseUrl + url.removePrefix("/")
        },
        style = style,
        recordLabel = recordLabel,
        components = components,
        discography = discography,
        albumLinks = albumLinks,
        headerLink = if (headerLink.startsWith("http")) headerLink else baseUrl + headerLink.removePrefix("/")
    )
}

/**
 * Mapper inverso:de BandDTO a BandEntity
 * Se usa para enviar datos al servidor (POST / PUT).
 */
fun BandDTO.toEntity(): BandEntity {
    return BandEntity(
        id = id,
        name = name,
        description = description,
        banner = banner,
        albumImages = albumImages,
        style = style,
        recordLabel = recordLabel,
        components = components,
        discography = discography,
        albumLinks = albumLinks,
        headerLink = headerLink
    )
}