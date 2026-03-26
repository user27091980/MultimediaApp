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