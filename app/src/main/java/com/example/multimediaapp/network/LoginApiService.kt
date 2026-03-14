package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LoginApiService {


    @GET("json/user/{id}")
    suspend fun getUser(@Path("user",) user: String): Response<LoginDTO>

    // Para el LOGIN: Se suelen pasar como Query o en el Body de un POST
    // Si tu API .NET lo espera por URL:
    @GET("json/users/{email}/{pass}")
    suspend fun loginUser(
        @Path("email") email: String,
        @Path("pass") pass: String
    ): Response<LoginDTO>


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