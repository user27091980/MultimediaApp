package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LoginApiService {
    // USER ENDPOINTS (Añadido "json/") ====
    @GET("json/users/{id}")
    suspend fun getUser(@Path("id") userId: String): Response<LoginDTO>

    @PUT("json/users/{id}")
    suspend fun updateUser(@Path("id") userId: String, @Body user: LoginDTO): Response<LoginDTO>

    @GET("json/users/info/{id}")
    suspend fun getUserInfo(@Path("id") userId: String): Response<LoginDTO>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"

        fun create(): LoginApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LoginApiService::class.java)
        }
    }
}