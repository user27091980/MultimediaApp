package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.UsersInfoEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserInfoApi {
    /**
     * Obtiene la información detallada de todos los usuarios
     */
    @GET("/users/info/")
    suspend fun getUsersInfo(): Response<List<UsersInfoEntity>>

    /**
     * Obtiene la información de un usuario específico por su ID
     */
    @GET("/info/{id}/")
    suspend fun getUserInfoById(@Path("id") id: String): Response<UsersInfoEntity>

    /**
     * Actualiza los datos del perfil del usuario (nombre, apellidos, etc.)
     */
    @PUT("/info/{id}/")
    suspend fun updateUserInfo(
        @Path("id") id: String,
        @Body userInfo: UsersInfoEntity
    ): Response<UsersInfoEntity>
}