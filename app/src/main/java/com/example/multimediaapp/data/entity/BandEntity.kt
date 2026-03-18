package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.BandDTO
import com.google.gson.annotations.SerializedName

/**
 * BandEntity representa cómo llegan los datos de una banda desde la API.
 * (Data Layer)
 */
data class BandEntity(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("banner")
    val banner: String,

    @SerializedName("albumImages")
    val albumImages: List<String>,

    @SerializedName("style")
    val style: String,

    @SerializedName("recordLabel")
    val recordLabel: String,

    @SerializedName("components")
    val components: String,

    @SerializedName("albumLinks")
    val albumLinks: List<String>,

    @SerializedName("headerLink")
    val headerLink: String
)

/**
 * Mapper: Entity → DTO
 */
fun BandEntity.toDTO(): BandDTO {

    val baseUrl = "http://10.0.2.2:5131/"

    return BandDTO(
        id = id,
        name = name,
        description = description,
        banner = buildUrl(baseUrl, banner),
        albumImages = albumImages.map { buildUrl(baseUrl, it) },
        style = style,
        recordLabel = recordLabel,
        components = components,
        albumLinks = albumLinks,
        headerLink = buildUrl(baseUrl, headerLink),

    )
}

/**
 * Mapper inverso: DTO → Entity
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
        albumLinks = albumLinks,
        headerLink = headerLink
    )
}

/**
 * Helper para construir URLs correctamente
 */
private fun buildUrl(baseUrl: String, path: String): String {
    return if (path.startsWith("http")) {
        path
    } else {
        baseUrl + path.removePrefix("/")
    }
}