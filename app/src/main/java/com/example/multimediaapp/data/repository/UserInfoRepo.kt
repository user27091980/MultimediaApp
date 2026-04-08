package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.UsersInfoEntity
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.RegisterRequestDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.network.UserInfoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Interfaz que define el contrato del repositorio de información de usuarios.
 *
 * Permite desacoplar la implementación de la capa de datos de la capa de presentación,
 * facilitando pruebas unitarias y cambios futuros en la implementación (por ejemplo, cambiar la fuente de datos).
 */
interface IUserInfoRepo {
    suspend fun getUserInfo(userId: String): UsersInfoDTO
    suspend fun register(
        email: String, name: String, passwd: String
    ): Result<UsersInfoDTO>
}

class UserInfoRepo(private val api: UserInfoApiService) : IUserInfoRepo {
    override suspend fun register(
        email: String,
        name: String,
        passwd: String, // <--- Coincide con la interfaz

    ): Result<UsersInfoDTO> = withContext(Dispatchers.IO) {
        try {
            val request = RegisterRequestDTO(email, name, passwd)
            val response = api.registerUser(request) // IMPORTANTE: @POST("auth/register")

            if (response.isSuccessful) {
                val body = response.body() ?: throw Exception("Respuesta vacía")
                // toDTO() debe manejar los nulos del 201 Created
                Result.success(body.toDTO())
            } else {
                Result.failure(Exception("Error servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserInfo(userId: String): UsersInfoDTO = withContext(Dispatchers.IO) {
        val response = api.getUser(userId)
        if (response.isSuccessful) {
            response.body()?.toDTO() ?: throw Exception("Vacío")
        } else throw Exception("Error")
    }
}
