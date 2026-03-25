package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.BandEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BandApiService {

    // BAND ENDPOINTS")
    // Obtener todas las bandas
    @GET("json/bands") // Asegúrate de si llevan "json/" delante o no
    suspend fun getBands(): Response<List<BandEntity>>

    // Obtener UNA banda completa (aquí ya viene descripción, estilo, etc.)
    @GET("json/bands/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandEntity>

    @GET("json/bands/{name}")
    suspend fun getBandByName(@Path("name") id: String): Response<BandEntity>

    @GET("json/bands/{description}")
    suspend fun getBandDescription(@Path("description") id: String): Response<BandEntity>

    // Para imagenes
    @GET("json/images/{id}")
    suspend fun getAlbumImages(@Path("id") id: String): Response<List<String>>

    @POST("json/bands")
    suspend fun createBand(@Body band: BandEntity): Response<BandEntity>

    @PUT("json/bands/{id}")
    suspend fun updateBand(@Path("id") id: String, @Body band: BandEntity): Response<BandEntity>

    @DELETE("json/bands/{id}")
    suspend fun deleteBand(@Path("id") id: String): Response<Unit>


}
/*
 * Esta interfaz define los endpoints de la API relacionados con las bandas.
 *
 * Utiliza Retrofit para realizar peticiones HTTP al servidor de forma
 * sencilla y declarativa.
 *
 * Cada función representa un endpoint de la API y está anotada con
 * el tipo de operación HTTP correspondiente:
 *
 * - @GET: obtener datos del servidor
 * - @POST: crear nuevos datos
 * - @PUT: actualizar datos existentes
 * - @DELETE: eliminar datos
 *
 * Todas las funciones son suspend, lo que permite ejecutar las llamadas
 * de red de forma asíncrona usando corrutinas, evitando bloquear la UI.
 *
 * RESPUESTA:
 * Todas las llamadas devuelven un Response<T>, lo que permite:
 * - Verificar si la petición fue exitosa (isSuccessful)
 * - Obtener el cuerpo de la respuesta (body())
 * - Manejar errores HTTP
 *
 * ENDPOINTS DEFINIDOS:
 *
 * 1. getBands()
 * - GET "json/bands"
 * - Obtiene una lista de todas las bandas.
 *
 * 2. getBandById(id)
 * - GET "json/bands/{id}"
 * - Obtiene una banda específica por su ID.
 *
 * 3. getBandByName(name)
 * - GET "json/bands/{name}"
 * - Busca una banda por su nombre.
 *
 * 4. getBandDescription(description)
 * - GET "json/bands/{description}"
 * - Busca bandas por descripción.
 *
 * 5. getAlbumImages(id)
 * - GET "json/images/{id}"
 * - Obtiene la lista de imágenes asociadas a una banda.
 *
 * 6. createBand(band)
 * - POST "json/bands"
 * - Crea una nueva banda enviando un BandEntity en el cuerpo.
 *
 * 7. updateBand(id, band)
 * - PUT "json/bands/{id}"
 * - Actualiza una banda existente.
 *
 * 8. deleteBand(id)
 * - DELETE "json/bands/{id}"
 * - Elimina una banda por su ID.
 *
 * ANOTACIONES IMPORTANTES:
 *
 * - @Path:
 *   Se utiliza para sustituir valores dinámicos en la URL.
 *
 * - @Body:
 *   Envía un objeto (BandEntity) en el cuerpo de la petición.
 *
 * - BandEntity:
 *   Es el modelo que representa cómo llegan o se envían los datos
 *   desde/hacia la API (capa de datos).
 *
 * IMPORTANTE:
 * - Asegurarse de que los endpoints coincidan con el backend.
 * - Revisar si los paths realmente existen (por ejemplo, {name}, {description}).
 * - Mantener consistencia en las rutas para evitar errores.
 *
 * Esta interfaz forma parte de la capa de red y es utilizada por
 * los repositorios para obtener y enviar datos al servidor.
 */