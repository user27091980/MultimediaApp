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
