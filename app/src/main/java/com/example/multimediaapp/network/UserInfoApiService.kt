package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.UsersInfoEntity
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * API para gestionar la información de los usuarios.
 * Mantiene coherencia de rutas y usa DTOs en el body para seguridad.
 */
interface UserInfoApiService {

    // Obtener lista completa de usuarios
    @GET("userInfo") // Quitamos la barra final innecesaria
    suspend fun getUsersInfo(): Response<List<UsersInfoEntity>>

    // Obtener un usuario específico por ID
    @GET("userInfo/{id}")
    suspend fun getUserInfoById(@Path("id") id: String): Response<UsersInfoEntity>

    // Registrar un nuevo usuario
    @POST("userInfo/register")
    suspend fun registerUser(
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>

    // Actualizar un usuario existente por ID
    @PUT("userInfo/{id}") // Unificamos el prefijo para que sea consistente
    suspend fun updateUserInfo(
        @Path("id") id: String,
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>
}