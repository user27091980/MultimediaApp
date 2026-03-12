package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.BandEntity
import com.example.multimediaapp.data.entity.MainEntity
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // ==== BAND ENDPOINTS (Añadido "json/") ====
    @GET("json/bands")
    suspend fun getBands(): Response<List<BandEntity>>

    @GET("json/bands/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandEntity>

    @POST("json/bands")
    suspend fun createBand(@Body band: BandEntity): Response<BandEntity>

    @PUT("json/bands/{id}")
    suspend fun updateBand(@Path("id") id: String, @Body band: BandEntity): Response<BandEntity>

    @DELETE("json/bands/{id}")
    suspend fun deleteBand(@Path("id") id: String): Response<Unit>

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


    // USER ENDPOINTS (Añadido "json/") ====
    @GET("json/users/{id}")
    suspend fun getUser(@Path("id") userId: String): Response<LoginDTO>

    @PUT("json/users/{id}")
    suspend fun updateUser(@Path("id") userId: String, @Body user: LoginDTO): Response<LoginDTO>

    @GET("json/users/info/{id}")
    suspend fun getUserInfo(@Path("id") userId: String): Response<UsersInfoDTO>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}