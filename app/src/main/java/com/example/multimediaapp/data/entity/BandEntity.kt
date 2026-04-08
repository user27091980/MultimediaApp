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

* BandEntity
*
* Esta clase representa los datos de una banda tal y como llegan desde la API.
* Pertenece a la capa de datos (Data Layer) y se utiliza para mapear la respuesta JSON.
*
* Cada propiedad está anotada con @SerializedName para asociar los campos del JSON
* con las variables del modelo.
*
* Incluye funciones de conversión (mappers) para transformar entre Entity y DTO:
*
* * toDTO():
* Convierte un BandEntity en un BandDTO para su uso en otras capas de la aplicación.
* También se encarga de construir correctamente las URLs de imágenes y recursos.
* Si una URL no es absoluta, se le añade una baseUrl.
*
* * toEntity():
* Convierte un BandDTO de vuelta a BandEntity.
* Se utiliza cuando se necesita enviar o almacenar datos en el formato de la API.
*
* Además, incluye una función auxiliar (buildUrl) que construye URLs completas,
* evitando errores cuando las rutas son relativas.
*
* Este enfoque permite separar la lógica de red (Entity) de la lógica de aplicación (DTO),
* facilitando el mantenimiento y la escalabilidad del proyecto.
  */

