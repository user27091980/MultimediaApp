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
/*
 * Este archivo define el repositorio de autenticación (LoginRepo),
 * encargado de gestionar las operaciones relacionadas con el usuario
 * y la autenticación contra la API.
 *
 * Utiliza una instancia centralizada de Retrofit (RetrofitModule)
 * para realizar las peticiones al servidor.
 *
 * Este repositorio actúa como intermediario entre la capa de red
 * y la lógica de la aplicación, centralizando la gestión de llamadas
 * y el manejo de errores.
 *
 * Métodos principales:
 *
 * 1. getUserById(id):
 *    - Realiza una petición al servidor para obtener un usuario por su ID.
 *    - Si la respuesta es correcta y contiene datos, devuelve un Result.success.
 *    - Si falla, devuelve un Result.failure con el error correspondiente.
 *    - Gestiona errores de red (IOException) y otros errores generales.
 *
 * 2. login(email, pass):
 *    - Envía las credenciales al servidor para autenticar al usuario.
 *    - Si el login es correcto, devuelve un objeto LoginDTO con los datos del usuario.
 *    - Por seguridad, no se guarda la contraseña en el objeto de respuesta.
 *    - Si las credenciales son incorrectas, devuelve un error.
 *
 * Uso de Result:
 * - Permite manejar tanto el éxito como el fallo de forma estructurada.
 * - Evita el uso de null y facilita el manejo de errores en la capa superior.
 *
 * Este repositorio ayuda a mantener separada la lógica de negocio de la
 * implementación de red, siguiendo una arquitectura limpia y mantenible.
 */