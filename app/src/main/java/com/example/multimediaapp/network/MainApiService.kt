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
/**
 * Interfaz que define las operaciones de red (API) para la entidad MainEntity.
 */
interface MainApiService {

    // Obtiene la lista completa. Ruta: http://10.0.2.2:5131/main/
    @GET(".")
    suspend fun getMainBands(): Response<List<MainEntity>>

    // Obtiene una imagen por ID.
    // Al empezar con "/", salta a la raíz: http://10.0.2.2{id}/
    @GET("/images/{id}/")
    suspend fun getMainImages(@Path("id") id: String): Response<MainEntity>

    // Obtiene una banda por ID. Ruta: http://10.0.2.2:5131/main/{id}/
    @GET("{id}/")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>

    // Crea una nueva banda en la raíz de /main/
    @POST(".")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>

    // Actualiza una banda. Ruta: http://10.0.2.2:5131/main/{id}
    @PUT("{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>

    // Elimina una banda. Ruta: http://10.0.2.2:5131/main/{id}
    @DELETE("{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>

    companion object {
        // La URL base DEBE terminar en /
        private const val BASE_URL = "http://10.0.2.2:5131/main/"

        fun create(): MainApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MainApiService::class.java)
        }
    }
}