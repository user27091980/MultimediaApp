package com.example.multimediaapp.data.entity
import com.example.multimediaapp.model.MainDTO
import com.google.gson.annotations.SerializedName

/**
 * MainEntity
 *
 * Representa los datos que llegan desde la API para la pantalla principal.
 * Esta clase pertenece a la capa de datos y se transforma en MainDTO
 * para ser utilizada dentro de la aplicación.
 */
data class MainEntity(
    val id: String,
    @SerializedName("bandName") val bandName: String?,
    @SerializedName("imageBand") val imageBand: String?
)

/**
 * Mapper de MainEntity a MainDTO
 */
fun MainEntity.toDTO(): MainDTO {

    val baseUrl = "http://10.0.2.2:5131/resources/"

    return MainDTO(
        id = id?:"",
        bandName = bandName ?:"",
        // Si la imagen ya es una URL completa la usamos directamente
        // Si no, añadimos la baseUrl
        imageBand = if (imageBand?.startsWith("http")==true) {
            imageBand
        } else {
            // Usamos removePrefix("/") por si el servidor envía "/foto.jpg" y evitar "//"
            baseUrl + (imageBand ?: "").removePrefix("/")
        }
    )
}

/**
 * Mapper de MainDTO a MainEntity, Se usa si necesitamos enviar datos al servidor (POST / PUT).
 *
 */
fun MainDTO.toEntity(): MainEntity = MainEntity(
    id = id,
    bandName = bandName,
    imageBand = imageBand
)
