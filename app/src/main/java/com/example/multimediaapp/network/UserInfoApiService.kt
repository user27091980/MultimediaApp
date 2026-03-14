package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.UsersInfoEntity
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserInfoApiService {

    @GET("json/users/info") // Quitamos la / inicial y añadimos prefijo json si tu API lo usa
    suspend fun getUsersInfo(): Response<List<UsersInfoEntity>>

    @GET("json/users/info/{id}")
    suspend fun getUserInfoById(@Path("id") id: String): Response<UsersInfoEntity>

    /**
     * Registro/Actualización: Enviamos el DTO en el CUERPO de la petición.
     * Es mucho más seguro y limpio que pasarlo todo por la URL.
     */
    @POST("json/users/register")
    suspend fun registerUser(
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>

    @PUT("json/users/info/{id}")
    suspend fun updateUserInfo(
        @Path("id") id: String,
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>
}