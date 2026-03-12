package com.example.multimediaapp.data.entity

import com.example.multimediaapp.model.BandDTO
import com.google.gson.annotations.SerializedName

/**
 * BandEntity representa cómo llegan los datos de una banda desde la API.
 * Vive en la capa de datos (Data Layer) y se convierte a BandDTO para la app.
 */
data class BandEntity(
    // Identificador único
    val id: String,

    // Nombre de la banda (JSON: "nombre")
    @SerializedName("name") val name: String,

    // Descripción o información de la banda (JSON: "texto")
    @SerializedName("description") val description: String,

    // Imagen de cabecera (banner) (JSON: "cabecera")
    @SerializedName("banner") val banner: String,

    // Lista de URLs de imágenes de los álbumes (JSON: "discos")
    @SerializedName("albumImages") val albumImages: List<String>,

    // Estilo musical (JSON: "estilo")
    @SerializedName("style") val style: String,

    // Discográfica (JSON: "discografica")
    @SerializedName("recordLabel") val recordLabel: String,

    // Componentes / miembros de la banda (JSON: "componentes")
    @SerializedName("components") val components: String,

    // Lista de nombres de los discos (JSON: "discografia")
    @SerializedName("discography") val discography: List<String>,

    // Lista de enlaces a los discos / canciones (JSON: "albumLinks")
    @SerializedName("albumLinks") val albumLinks: List<String>,

    // Enlace a la imagen de cabecera (JSON: "enlaces")
    @SerializedName("headerLink") val headerLink: String

)

/**
 * Mapper de BandEntity → BandDTO
 * Permite trabajar con un modelo de dominio desacoplado de la API
 */
/**
 * Mapper de BandEntity → BandDTO
 * Incluye la lógica para completar las URLs de las imágenes.
 */
fun BandEntity.toDTO(): BandDTO {
    // Esta es la base de tu servidor local en el emulador.
    // Si las fotos están en otra carpeta (ej: /images/), ajústalo aquí.
    val baseUrl = "http://10.0.2.2:5131/json/main/"

    return BandDTO(
        id = id,
        name = name,
        description = description,

        // 1. Corregimos el Banner: Si no empieza por http, le ponemos la base
        banner = if (banner.startsWith("http")) banner else "$baseUrl$banner",

        // 2. Corregimos la lista de imágenes de discos (mapeamos cada una)
        albumImages = albumImages.map { url ->
            if (url.startsWith("http")) url else "$baseUrl$url"
        },

        style = style,
        recordLabel = recordLabel,
        components = components,
        discography = discography,
        albumLinks = albumLinks,

        // 3. Corregimos el enlace de cabecera si fuera una imagen
        headerLink = if (headerLink.startsWith("http")) headerLink else "$baseUrl$headerLink"
    )
}