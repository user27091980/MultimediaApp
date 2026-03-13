package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.BandDTO
import com.google.gson.annotations.SerializedName

/**
 * BandEntity representa cómo llegan los datos de una banda desde la API.
 * Vive en la capa de datos (Data Layer) y se convierte a BandDTO para la app.
 */
/**
 * BandEntity representa cómo llegan los datos de una banda desde la API.
 */
data class BandEntity(
    val id: String,

    // CORREGIDO: Los nombres en @SerializedName deben coincidir con las llaves del JSON de tu API
    @SerializedName("nombre") val name: String,
    @SerializedName("texto") val description: String,
    @SerializedName("cabecera") val banner: String,
    @SerializedName("discos") val albumImages: List<String>,
    @SerializedName("estilo") val style: String,
    @SerializedName("discografica") val recordLabel: String,
    @SerializedName("componentes") val components: String,
    @SerializedName("discografia") val discography: List<String>,
    @SerializedName("albumLinks") val albumLinks: List<String>,
    @SerializedName("enlaces") val headerLink: String
)

/**
 * Mapper de BandEntity → BandDTO
 */
fun BandEntity.toDTO(): BandDTO {
    // CORREGIDO: Apuntamos a la carpeta /images/ que confirmaste
    val baseUrl = "http://10.0.2.2"

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
 * NUEVO/CORREGIDO: Mapper inverso para poder enviar datos al servidor (POST/PUT)
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