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
    @SerializedName("nombre") val name: String,

    // Descripción o información de la banda (JSON: "texto")
    @SerializedName("texto") val description: String,

    // Imagen de cabecera (banner) (JSON: "cabecera")
    @SerializedName("cabecera") val headerImage: String,

    // Lista de URLs de imágenes de los álbumes (JSON: "discos")
    @SerializedName("discos") val albumImages: List<String>,

    // Estilo musical (JSON: "estilo")
    @SerializedName("estilo") val style: String,

    // Discográfica (JSON: "discografica")
    @SerializedName("discografica") val recordLabel: String,

    // Componentes / miembros de la banda (JSON: "componentes")
    @SerializedName("componentes") val components: String,

    // Lista de nombres de los discos (JSON: "discografia")
    @SerializedName("discografia") val discography: List<String>,

    // Lista de enlaces a los discos / canciones (JSON: "albumLinks")
    @SerializedName("albumLinks") val albumLinks: List<String>,

    // Enlace a la imagen de cabecera (JSON: "enlaces")
    @SerializedName("enlaces") val headerLink: String


)

/**
 * Mapper de BandEntity → BandDTO
 * Permite trabajar con un modelo de dominio desacoplado de la API
 */
fun BandEntity.toDTO(): BandDTO = BandDTO(
    id = id,
    name = name,
    description = description,
    headerImage = headerImage,
    albumImages = albumImages,
    style = style,
    recordLabel = recordLabel,
    components = components,
    discography = discography,
    albumLinks = albumLinks,
    headerLink = headerLink

)