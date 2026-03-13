package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.MainEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
/**
 * Interfaz que define las operaciones de red (API) para la entidad MainEntity.
 */
interface MainApiService {
    // ==== MAIN ENTITY ENDPOINTS (Añadido "json/") ====
    // Obtiene la lista completa de bandas/elementos
    @GET("main")
    suspend fun getMainBands(): Response<List<MainEntity>>
    // Obtiene una imagen específica mediante su ID
    @GET("images/{id}/")
    suspend fun getMainImages(@Path("id") id: String): Response<MainEntity>
    // Obtiene los detalles de una banda específica por su ID
    @GET("main/{id}/")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>
    // Crea una nueva banda enviando un objeto MainEntity en el cuerpo de la petición
    @POST("main")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>
    @PUT("main/{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>
    // Elimina una banda según su ID
    @DELETE("main/{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>
    /**
     * Objeto de acompañamiento para centralizar la configuración de Retrofit.
     */
    companion object {
        // IP especial para que el emulador de Android acceda al localhost de tu equipo
        private const val BASE_URL = "http://10.0.2.2:5131/main/"
        /**
         * Crea y configura la instancia de Retrofit para usar esta interfaz.
         */
        fun create(): MainApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MainApiService::class.java)
        }
    }
}