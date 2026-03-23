package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApiService {


    @GET("json/user/{id}")
    suspend fun getUser(@Path("id",) id: String): Response<LoginDTO>

    // Para el LOGIN: Se suelen pasar como Query o en el Body de un POST
    // Si tu API .NET lo espera por URL:
    @POST("json/user/login")
    suspend fun loginUser(email: String, @Body login: String): Response<LoginDTO>
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