package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.MainEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MainApiService {

    //Para obtener la lista completa
    @GET("json/main")
    suspend fun getMainBands(): Response<List<MainEntity>>

    //Obtener una sola banda por ID
    @GET("json/main/{id}")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>

    // Rutas específicas para evitar conflictos
    // Si quieres buscar por nombre o imagen, la URL debe ser distinta:
    @GET("json/main/bandName/{name}")
    suspend fun getNameBand(@Path("name") name: String): Response<MainEntity>

    @GET("json/resources/{id}")
    suspend fun getImages(@Path("id") id: String): Response<MainEntity>

    //(CRUD)
    @POST("json/main")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>
    //actualizar banda
    @PUT("json/main/{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>
    //eliminar banda
    @DELETE("json/main/{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>
}
/*
 * Esta interfaz define los endpoints de la API relacionados con la
 * información principal de las bandas (pantalla Main).
 *
 * Utiliza Retrofit para realizar peticiones HTTP de forma declarativa.
 * Forma parte de la capa de red (Network Layer).
 *
 * Todas las funciones son suspend, lo que permite ejecutar las llamadas
 * de forma asíncrona usando corrutinas sin bloquear la interfaz de usuario.
 *
 * RESPUESTA:
 * Cada función devuelve un Response<T>, lo que permite:
 * - Verificar si la petición fue exitosa (isSuccessful)
 * - Acceder al cuerpo de la respuesta (body())
 * - Manejar errores HTTP
 *
 * ENDPOINTS:
 *
 * 1. getMainBands():
 * - @GET("json/main")
 * - Obtiene la lista completa de bandas para la pantalla principal.
 *
 * 2. getMainBandById(id):
 * - @GET("json/main/{id}")
 * - Obtiene una banda específica por su ID.
 *
 * 3. getNameBand(name):
 * - @GET("json/main/bandName/{name}")
 * - Busca una banda por su nombre.
 * - Se usa una ruta separada para evitar conflictos con otros endpoints.
 *
 * 4. getImages(id):
 * - @GET("json/resources/{id}")
 * - Obtiene información relacionada con imágenes asociadas a una banda.
 *
 * OPERACIONES CRUD:
 *
 * 5. createMainBand(band):
 * - @POST("json/main")
 * - Crea una nueva banda enviando un objeto MainEntity en el cuerpo.
 *
 * 6. updateMainBand(id, band):
 * - @PUT("json/main/{id}")
 * - Actualiza una banda existente identificada por su ID.
 *
 * 7. deleteMainBand(id):
 * - @DELETE("json/main/{id}")
 * - Elimina una banda del servidor.
 *
 * ANOTACIONES:
 *
 * - @GET, @POST, @PUT, @DELETE:
 *   Indican el tipo de operación HTTP.
 *
 * - @Path:
 *   Permite insertar valores dinámicos dentro de la URL.
 *
 * - @Body:
 *   Envía un objeto (MainEntity) como cuerpo de la petición.
 *
 * IMPORTANTE:
 * - Verificar que las rutas coincidan con el backend.
 * - Mantener consistencia en los endpoints para evitar errores.
 * - Asegurar que el backend soporte cada ruta definida.
 *
 * Este servicio es utilizado por el repositorio para separar la lógica
 * de red de la lógica de negocio, siguiendo una arquitectura limpia.
 */