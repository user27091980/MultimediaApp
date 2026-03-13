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
    // ==== MAIN ENTITY ENDPOINTS (Añadido "json/") ====
    @GET("json/main")
    suspend fun getMainBands(): Response<List<MainEntity>>

    @GET("json/main/{images}")
    suspend fun getMainImages(@Path("images") images: String): Response<MainEntity>

    @GET("json/main/{id}")
    suspend fun getMainBandById(@Path("id") id: String): Response<MainEntity>

    @POST("json/main")
    suspend fun createMainBand(@Body band: MainEntity): Response<MainEntity>

    @PUT("json/main/{id}")
    suspend fun updateMainBand(@Path("id") id: String, @Body band: MainEntity): Response<MainEntity>

    @DELETE("json/main/{id}")
    suspend fun deleteMainBand(@Path("id") id: String): Response<Unit>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"

        fun create(): MainApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MainApiService::class.java)
        }
    }
}