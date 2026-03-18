package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.retrofit.RetrofitModule.userInfoApi
import com.example.multimediaapp.session.SessionManager
import java.io.IOException

class UsersInfoRepo(context: Context) {

    private val session = SessionManager(context)
    private val api = RetrofitModule.userInfoApi

    /**
     * Obtiene la información de un usuario por su ID.
     */
    suspend fun read(id: String): Result<UsersInfoDTO?> {
        return try {
            val response = userInfoApi.getUserInfoById(id)
            if (response.isSuccessful) {
                // Importante: toDTO() debe estar definido en tu Entity
                Result.success(response.body()?.toDTO())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * REGISTRO: Construye el DTO y lo envía a la API.
     */
    suspend fun register(
        email: String,
        name: String,
        pass: String,
        lastName: String,
        country: String
    ): Result<UsersInfoDTO> {
        return try {
            // 1. Creamos el DTO con los datos recibidos del VM
            val dto = UsersInfoDTO(
                id = "",
                email = email,
                name = name,
                pass = pass,
                lastName = lastName,
                country = country
            )

            // 2. Enviamos el objeto completo al Body de la petición
            val response = api.registerUser(dto)

            if (response.isSuccessful && response.body() != null) {
                val registeredUser = response.body()!!.toDTO()
                session.saveUser(registeredUser)
                Result.success(registeredUser)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error en el registro"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Actualiza el perfil del usuario.
     */
    suspend fun update(dto: UsersInfoDTO): Result<UsersInfoDTO> {
        return try {
            val response = api.updateUserInfo(dto.email, dto)
            if (response.isSuccessful && response.body() != null) {
                val updated = response.body()!!.toDTO()
                session.saveUser(updated)
                Result.success(updated)
            } else {
                Result.failure(Exception("No se pudo actualizar el perfil"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getLoggedUser(): UsersInfoDTO? = session.getUser()
    fun logout() = session.logout()
}