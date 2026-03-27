package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.BandEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * BandApiService:
 *
 * Interfaz que define los **endpoints** de la API para la entidad Band.
 *
 * Usamos Retrofit para:
 * - Declarar rutas HTTP
 * - Mapear automáticamente JSON ↔ objetos Kotlin
 * - Manejar respuestas de manera segura con `Response<T>`
 *
 * Cada función corresponde a una operación CRUD o de consulta.
 */
interface BandApiService {

    // ================================
    // OBTENER DATOS
    // ================================

    /** Obtiene la lista completa de bandas desde el servidor */
    @GET("json/bands")
    suspend fun getBands(): Response<List<BandEntity>>

    /** Obtiene una banda específica por su ID */
    @GET("json/bands/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandEntity>

    /** Obtiene una banda específica por su nombre */
    @GET("json/bands/{name}")
    suspend fun getBandByName(@Path("name") id: String): Response<BandEntity>

    /** Obtiene la descripción de una banda por su texto descriptivo */
    @GET("json/bands/{description}")
    suspend fun getBandDescription(@Path("description") id: String): Response<BandEntity>

    /** Obtiene la lista de URLs de imágenes de un álbum por ID de banda */
    @GET("json/images/{id}")
    suspend fun getAlbumImages(@Path("id") id: String): Response<List<String>>

    // ================================
    // CREAR / MODIFICAR / ELIMINAR
    // ================================

    /** Crea una nueva banda en el servidor */
    @POST("json/bands")
    suspend fun createBand(@Body band: BandEntity): Response<BandEntity>

    /** Actualiza los datos de una banda existente por su ID */
    @PUT("json/bands/{id}")
    suspend fun updateBand(@Path("id") id: String, @Body band: BandEntity): Response<BandEntity>

    /** Elimina una banda por su ID, devuelve un Response<Unit> para indicar éxito */
    @DELETE("json/bands/{id}")
    suspend fun deleteBand(@Path("id") id: String): Response<Unit>
}

/**
 * NOTAS:
 *
 * 1. `suspend` → todas las funciones son **coroutines**, ejecutadas de forma asíncrona.
 * 2. `Response<T>` → nos permite:
 *    - Verificar `isSuccessful`
 *    - Obtener el código HTTP
 *    - Manejar cuerpo de respuesta nulo
 * 3. `@Path` → sustituye el valor dentro de la URL.
 * 4. `@Body` → convierte automáticamente un objeto Kotlin en JSON al hacer POST o PUT.
 * 5. Organización:
 *    - GET → lectura
 *    - POST → creación
 *    - PUT → actualización
 *    - DELETE → eliminación
 */