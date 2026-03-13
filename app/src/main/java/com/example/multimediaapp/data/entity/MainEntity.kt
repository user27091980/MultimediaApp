package com.example.multimediaapp.data.entity
import com.example.multimediaapp.model.MainDTO
import com.google.gson.annotations.SerializedName

data class MainEntity(
    val id: String,
    @SerializedName("bandName") val bandName: String,
    @SerializedName("imageBand") val imageBand: String
)

/**
 * Mapper de MainEntity → MainDTO
 */
fun MainEntity.toDTO(): MainDTO {

    val baseUrl = "http://10.0.2.2:5131/images"

    return MainDTO(
        id = id,
        bandName = bandName,
        // CORRECCIÓN LÓGICA:
        // Si imageBand es "foto.jpg", el resultado será "http://10.0.2.2foto.jpg"
        imageBand = if (imageBand.startsWith("http")) {
            imageBand
        } else {
            // Usamos removePrefix("/") por si el servidor envía "/foto.jpg" y evitar "//"
            baseUrl + imageBand.removePrefix("/")
        }
    )
}

/**
 * Mapper de MainDTO → MainEntity
 */
fun MainDTO.toEntity(): MainEntity = MainEntity(
    id = id,
    bandName = bandName,
    imageBand = imageBand
)
