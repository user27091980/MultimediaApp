package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.BandEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BandApiService {

    // BAND ENDPOINTS")
    // Obtener todas las bandas
    @GET("api/bands") // Asegúrate de si llevan "json/" delante o no
    suspend fun getBands(): Response<List<BandEntity>>

    // Obtener UNA banda completa (aquí ya viene descripción, estilo, etc.)
    @GET("api/bands/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandEntity>

    // Para imagenes
    @GET("api/images/{id}")
    suspend fun getAlbumImages(@Path("id") id: String): Response<List<String>>

    @POST("api/bands")
    suspend fun createBand(@Body band: BandEntity): Response<BandEntity>

    @PUT("api/bands/{id}")
    suspend fun updateBand(@Path("id") id: String, @Body band: BandEntity): Response<BandEntity>

    @DELETE("api/bands/{id}")
    suspend fun deleteBand(@Path("id") id: String): Response<Unit>


}