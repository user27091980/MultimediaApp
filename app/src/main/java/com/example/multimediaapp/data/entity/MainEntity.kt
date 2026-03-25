package com.example.multimediaapp.data.entity
import com.example.multimediaapp.model.BandDTO
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

    val baseUrl = "http://10.0.2.2:5131/"

    return MainDTO(
        id = id ?:"",
        bandName = bandName ?:"",
        // Si la imagen ya es una URL completa la usamos directamente
        // Si no, añadimos la baseUrl
        imageBand = if (imageBand?.startsWith("http")==true) {
            imageBand
        } else {
            // Usamos removePrefix("/") por si el servidor envía "/foto.jpg" y evitar "//"
            baseUrl + "images/"+ (imageBand ?: "").removePrefix("/")
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

fun BandDTO.toMainDTO(): MainDTO {
    return MainDTO(
        id = id,
        bandName = name,
        imageBand = banner // 👈 aquí decides qué imagen usar
    )
}

/*
 * Este archivo define la clase MainEntity, que representa los datos que llegan desde la API
 * para la pantalla principal de la aplicación.
 *
 * Pertenece a la capa de datos (Data Layer) y su función es mapear la respuesta del servidor
 * a un objeto que pueda ser convertido y usado dentro de la app.
 *
 * Los campos bandName e imageBand pueden ser nulos, ya que la API puede no enviarlos siempre.
 * La anotación @SerializedName se utiliza para mapear los nombres del JSON con las propiedades
 * de la clase.
 *
 * La clase incluye funciones de mapeo:
 *
 * 1. toDTO():
 *    Convierte MainEntity en MainDTO, que es el modelo utilizado en la capa de presentación.
 *    - Se asegura de que los valores no sean nulos usando valores por defecto.
 *    - Gestiona las URLs de imágenes:
 *      - Si la imagen ya es una URL completa, se usa directamente.
 *      - Si es una ruta relativa, se le añade una baseUrl.
 *      - Se elimina el carácter "/" inicial para evitar errores en la URL.
 *
 * 2. toEntity():
 *    Convierte MainDTO en MainEntity.
 *    - Se utiliza cuando se envían datos al servidor (POST o PUT).
 *
 * 3. toMainDTO() (desde BandDTO):
 *    Permite transformar un objeto BandDTO a MainDTO.
 *    - Se usa para reutilizar información de una banda en la pantalla principal.
 *    - Se selecciona qué imagen usar (en este caso, el banner).
 *
 * Este enfoque permite mantener una separación clara entre:
 * - Los datos de la API (Entity)
 * - Los datos de la aplicación (DTO)
 * - La lógica de presentación
 *
 * Con esto se consigue una arquitectura más limpia, modular y mantenible.
 */