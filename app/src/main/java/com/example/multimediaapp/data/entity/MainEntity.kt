package com.example.multimediaapp.data.entity
import com.example.multimediaapp.model.MainDTO
import com.google.gson.annotations.SerializedName

data class MainEntity(
    val id: String,
    @SerializedName("bandName") val bandName: String,
    @SerializedName("imageBand") val imageBand: String
)

/**
 * Mapper que convierte MainEntity (capa de datos) a MainDTO (capa de dominio).
 * 
 * Construye la URL completa para que Coil pueda cargar la imagen.
 * Si el backend devuelve solo el nombre del archivo (ej: "tool1.jpg"),
 * se le añade el prefijo necesario para formar una URL válida.
 */
fun MainEntity.toDTO(): MainDTO {
    val baseUrl = "http://10.0.2.2/images/" // Asegúrate de que termine en /

    return MainDTO(
        id = id,
        bandName = bandName,
        // Si el JSON trae "foto.jpg", esto crea "http://10.0.2.2foto.jpg"
        imageBand = if (imageBand.startsWith("http")) imageBand else "$baseUrl$imageBand"
    )
}

fun MainDTO.toEntity(): MainEntity = MainEntity(
    id = id,
    bandName = bandName,
    imageBand = imageBand
)
