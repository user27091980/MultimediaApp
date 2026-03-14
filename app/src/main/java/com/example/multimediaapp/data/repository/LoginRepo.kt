package com.example.multimediaapp.data.repository

// Importa el modelo de usuario
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import java.io.IOException

class LoginRepo {

    // Usamos la instancia centralizada del módulo de Retrofit
    private val api = RetrofitModule.loginApi

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
    suspend fun login(email: String, pass: String): Result<LoginDTO> {
        return try {
            // Usamos el endpoint de login que definimos en LoginApiService
            val response = api.loginUser(email, pass)

            if (response.isSuccessful && response.body() != null) {
                // Aquí podrías mapear de UsersInfoDTO a LoginDTO si fuera necesario
                val body = response.body()
                val loginData = LoginDTO(
                    id = body!!.id,
                    email = body.email,
                    user = body.user,
                    pass = "" // Por seguridad no solemos persistir la pass tras el login
                )
                Result.success(loginData)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}