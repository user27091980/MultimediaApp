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
        banner = if (banner.startsWith("http")) {
            banner
        } else {
            baseUrl + "images/" + banner.removePrefix("/")
        },

        albumImages = albumImages.map { image ->
            if (image.startsWith("http")) {
                image
            } else {
                baseUrl + "images/" + image.removePrefix("/")
            }
        },
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
    val baseUrl = "http://10.0.2.2:5131/"
    return BandEntity(
        id = id,
        name = name,
        description = description,
        banner = banner,
        albumImages = albumImages.map { image ->
            if (image.startsWith("http")) {
                image
            } else {
                baseUrl + "images/" + image.removePrefix("/")
            }
        },
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

/*
 * Este archivo define la clase BandEntity, que representa cómo se reciben los datos de una banda
 * desde una API (capa de datos).
 *
 * La anotación @SerializedName se utiliza para mapear los campos del JSON recibido con los atributos
 * de la clase, permitiendo que los nombres coincidan aunque sean diferentes en la API.
 *
 * Esta clase contiene toda la información de una banda, incluyendo:
 * - Datos básicos (id, nombre, descripción)
 * - Imágenes (banner e imágenes del álbum)
 * - Información musical (estilo, discográfica, componentes)
 * - Enlaces (albumLinks y headerLink)
 *
 * Además, se incluyen funciones de mapeo (mappers) para transformar datos entre capas:
 *
 * 1. toDTO():
 *    Convierte un objeto BandEntity (datos de la API) a BandDTO (modelo que usa la aplicación).
 *    - Se encarga de transformar las URLs relativas en URLs completas.
 *    - Añade un baseUrl cuando la imagen no contiene "http".
 *    - Aplica lógica para asegurar que los enlaces sean válidos.
 *
 * 2. toEntity():
 *    Convierte un BandDTO en BandEntity.
 *    - Se usa cuando se envían datos hacia la API.
 *    - Mantiene o transforma los datos a formato compatible con el backend.
 *
 * 3. buildUrl():
 *    Función auxiliar que construye correctamente una URL.
 *    - Si la ruta ya es una URL completa, la devuelve sin cambios.
 *    - Si no, concatena el baseUrl con la ruta proporcionada.
 *
 * Este enfoque de mapeo ayuda a separar responsabilidades:
 * - La API trabaja con BandEntity
 * - La app trabaja con BandDTO
 * - Se evita acoplar directamente la lógica de red con la UI
 */