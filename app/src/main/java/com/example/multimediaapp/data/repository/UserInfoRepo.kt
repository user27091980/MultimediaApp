package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.session.SessionManager
// CORRECCIÓN: Faltaba importar IOException para el catch
import java.io.IOException
import kotlin.String

/**
 * Repositorio que gestiona la información de usuario.
 * Conecta la API de Retrofit con la sesión local.
 */
class UsersInfoRepo(context: Context) {

    private val session = SessionManager(context)
    private val api = RetrofitModule.userInfoApi

    /**
     * Obtiene la información de un usuario por su Email o ID desde el servidor.
     */
    suspend fun read(id: String): Result<UsersInfoDTO?> {
        return try {
            val response = api.getUserInfoById(id)
            if (response.isSuccessful) {
                // Convertimos la Entity que devuelve la API a DTO para la UI
                Result.success(response.body()?.toDTO())
            } else {
                Result.failure(Exception("Usuario no encontrado: ${response.code()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Error de red: verifica tu conexión"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Registra un nuevo usuario enviando el DTO al servidor.
     */
    suspend fun register(
        dto: String,
        user: String,
        name: String,
        pass: String,
        name1: String,
        lastName: String,
        country: String
    ): Result<UsersInfoDTO> {
        return try {
            // La API suele recibir el DTO directamente en el @Body
            val response = api.registerUser(dto)

            if (response.isSuccessful && response.body() != null) {
                val registeredUser = response.body()!!.toDTO()
                // Guardamos en sesión local tras el éxito
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
            val response = api.updateUserInfo(dto.user, dto)
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

    // --- Métodos de SesiónDelegados ---
    fun getLoggedUser(): UsersInfoDTO? = session.getUser()
    fun logout() = session.logout()
}