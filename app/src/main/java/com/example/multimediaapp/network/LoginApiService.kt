package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.LoginRequestDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApiService {

    @GET("json/user/{id}")
    suspend fun getUser(@Path("id") id: String): Response<LoginDTO>

    @POST("json/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequestDTO): Response<LoginDTO>

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