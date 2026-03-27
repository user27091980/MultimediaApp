package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.MainEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * MainApiService:
 *
 * Interfaz de Retrofit que define los **endpoints para la entidad principal (Main)**
 * que representa bandas en la pantalla principal de la aplicación.
 *
 * Funcionalidades:
 * - Obtener todas las bandas
 * - Obtener una banda específica por ID o por nombre
 * - CRUD completo (crear, actualizar, eliminar)
 * - Obtener recursos relacionados, como imágenes de la banda
 *
 * Notas sobre Retrofit:
 * - `@GET`, `@POST`, `@PUT`, `@DELETE` definen el tipo de petición HTTP
 * - `@Path` reemplaza valores dinámicos en la URL
 * - `@Body` serializa automáticamente un objeto Kotlin a JSON para POST/PUT
 * - `Response<T>` permite manejar códigos HTTP y respuestas nulas
 */
interface MainApiService {

    /** Obtiene la lista completa de bandas */
    @GET("json/main")
    suspend fun getMainBands(): Response<List<MainEntity>>

    /** Obtiene una sola banda por su ID */
    @GET("json/main/{id}")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>

    /** Obtiene una banda específica por su nombre (URL distinta para evitar conflictos) */
    @GET("json/main/bandName/{name}")
    suspend fun getNameBand(@Path("name") name: String): Response<MainEntity>

    /** Obtiene recursos o imágenes de una banda por ID */
    @GET("json/resources/{id}")
    suspend fun getImages(@Path("id") id: String): Response<MainEntity>

    /** Crea una nueva banda enviando un objeto MainEntity */
    @POST("json/main")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>

    /** Actualiza una banda existente enviando su ID y el objeto actualizado */
    @PUT("json/main/{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>

    /** Elimina una banda por su ID y devuelve un Response<Unit> para manejar éxito/error */
    @DELETE("json/main/{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>
}

/**
 * NOTAS IMPORTANTES:
 *
 * 1. Todas las funciones son `suspend` → se deben llamar dentro de coroutines.
 * 2. `Response<T>` → permite verificar `isSuccessful` y manejar errores de forma segura.
 * 3. Las rutas como `bandName/{name}` evitan conflictos con otras URLs dinámicas.
 * 4. Para obtener imágenes u otros recursos, se usan endpoints específicos (`resources/{id}`).
 * 5. Mantener las URLs organizadas evita errores y facilita pruebas y mantenimiento.
 */