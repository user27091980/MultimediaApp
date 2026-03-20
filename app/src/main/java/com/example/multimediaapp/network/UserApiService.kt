package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UserDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    // LOGIN
    @POST("auth/login")
    suspend fun loginUser(
        @Body login: LoginDTO
    ): Response<UserDTO>

    // REGISTER
    @POST("auth/register")
    suspend fun registerUser(
        @Body register: UserDTO
    ): Response<UserDTO>

    // GET USER BY ID
    @GET("json/user/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<UserDTO>

    companion object {

        private const val BASE_URL = "http://10.0.2.2:5131/"

        fun create(): UserApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApiService::class.java)
        }
    }
}

