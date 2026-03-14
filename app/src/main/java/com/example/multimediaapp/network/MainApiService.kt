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

interface MainApiService {

    // 1. Para obtener la LISTA completa, no suele llevar {id}
    @GET("json/main")
    suspend fun getMainBands(): Response<List<MainEntity>>

    // 2. Obtener una sola banda por ID
    @GET("json/main/{id}")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>

    // 3. Rutas específicas para evitar conflictos
    // Si quieres buscar por nombre o imagen, la URL debe ser distinta:
    @GET("json/main/name/{name}")
    suspend fun getNameBand(@Path("name") name: String): Response<MainEntity>

    @GET("json/main/images/{imageBand}")
    suspend fun getMainImages(@Path("imageBand") imageBand: String): Response<MainEntity>

    // 4. Operaciones de escritura (CRUD)
    @POST("json/main")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>

    @PUT("json/main/{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>

    @DELETE("json/main/{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>
}
