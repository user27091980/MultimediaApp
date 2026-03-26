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

* MainEntity
*
* Esta clase representa los datos que llegan desde la API para la pantalla principal.
* Pertenece a la capa de datos y se utiliza para mapear la respuesta JSON.
*
* Contiene información básica como el id, el nombre de la banda y la imagen.
* Algunos campos pueden ser nulos, por lo que se manejan valores por defecto
* al convertirlos a otros modelos.
*
* Incluye funciones de conversión (mappers):
*
* * toDTO():
* Convierte MainEntity en MainDTO para su uso en la aplicación.
* Se encarga de construir correctamente la URL de la imagen.
* Si la imagen ya es una URL completa, se usa directamente;
* si no, se añade una baseUrl para formar la ruta completa.
*
* * toEntity():
* Convierte MainDTO en MainEntity.
* Se utiliza cuando se necesita enviar datos al servidor (por ejemplo en POST o PUT).
*
* * BandDTO.toMainDTO():
* Permite convertir un objeto BandDTO en MainDTO.
* Se usa para adaptar datos de bandas a la estructura necesaria
* para la pantalla principal, reutilizando información como el nombre
* y la imagen (banner).
*
* Este enfoque permite separar la capa de datos de la lógica de la aplicación,
* facilitando el mantenimiento, la reutilización y la escalabilidad del código.
  */
