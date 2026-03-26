package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.LoginRequestDTO
import com.example.multimediaapp.network.LoginApiService
import java.io.IOException

class LoginRepo(private val api: LoginApiService = LoginApiService.create()) {

    /**
     * Obtiene un usuario específico por su ID desde el servidor.
     */
    suspend fun getUserById(id: String): Result<LoginDTO> {
        return try {
            val response = api.getUser(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Usuario no encontrado: ${response.code()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Error de red: Verifica tu conexión"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Intenta realizar el login enviando email y contraseña.
     * Devuelve el perfil completo del usuario si es exitoso.
     */
    suspend fun login(email: String, pass: String): LoginDTO {
        return try {
            val response = api.loginUser(LoginRequestDTO(email, pass))
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw Exception("Usuario o contraseña incorrecta")
            }
        } catch (e: IOException) {
            throw Exception("Error de red: Verifica tu conexión")
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error al iniciar sesión")
        }
    }
}