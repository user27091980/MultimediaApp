package com.example.multimediaapp.data.entity
import com.example.multimediaapp.model.MainDTO
import com.google.gson.annotations.SerializedName

data class MainEntity(
    val id: String,
    @SerializedName("bandName") val bandName: String,
    @SerializedName("imageBand") val imageBand: String
)

// Mapper corregido con la URL base de tu servidor
fun MainEntity.toDTO(): MainDTO {
    val baseUrl = "http://10.0.2.2:5131/json/main/" // Ruta a tus fotos

    return MainDTO(
        id = id,
        bandName = bandName,
        // Construimos la URL completa para Coil
        imageBand = if (imageBand.startsWith("http")) imageBand else "$baseUrl$imageBand"
    )
}
fun MainDTO.toEntity(): MainEntity = MainEntity(
    id = id,
    bandName = bandName,
    imageBand = imageBand
)