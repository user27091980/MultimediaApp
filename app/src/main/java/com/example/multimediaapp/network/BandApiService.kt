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

interface BandApiService{

    // ==== BAND ENDPOINTS (Añadido "json/") ====
    @GET("/band")
    suspend fun getBands(): Response<List<BandEntity>>

    @GET("/band/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandEntity>

    @GET("images")
    suspend fun getImageById(@Path("id") id:String):Response<BandEntity>

    @PUT("band/{id}")
    suspend fun updateBand(@Path("id") id: String, @Body band: BandEntity): Response<BandEntity>

    @POST("band/{id}")
    suspend fun createBand(@Body band: BandEntity): Response<BandEntity>
    @DELETE("bands/{id}")
    suspend fun deleteBand(@Path("id") id: String): Response<Unit>
    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/band/"

        fun create(): BandApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(BandApiService::class.java)
        }
    }


}