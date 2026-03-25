package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.retrofit.RetrofitModule.userInfoApi
import com.example.multimediaapp.session.SessionManager

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

/*
 * Este archivo define el repositorio de usuarios (UsersInfoRepo),
 * encargado de gestionar las operaciones relacionadas con el perfil del usuario
 * y la comunicación con la API.
 *
 * Utiliza Retrofit para realizar las peticiones al servidor y SessionManager
 * para gestionar la sesión del usuario en local.
 *
 * Este repositorio actúa como intermediario entre:
 * - La capa de red (API)
 * - La capa de datos (Entity)
 * - La capa de presentación (DTO)
 * - La gestión de sesión (SessionManager)
 *
 * Métodos principales:
 *
 * 1. read(id):
 *    - Obtiene la información de un usuario por su ID.
 *    - Llama al endpoint correspondiente de la API.
 *    - Convierte la respuesta (Entity) a DTO.
 *    - Devuelve un Result con éxito o fallo.
 *
 * 2. register(email, name, pass, lastName, country):
 *    - Construye un objeto UsersInfoDTO con los datos proporcionados.
 *    - Envía el DTO al servidor para registrar un nuevo usuario.
 *    - Si el registro es exitoso:
 *        - Convierte la respuesta a DTO.
 *        - Guarda el usuario en sesión mediante SessionManager.
 *    - Si falla, devuelve el error correspondiente.
 *
 * 3. update(dto):
 *    - Actualiza los datos del usuario enviando el DTO a la API.
 *    - Usa el email como identificador del usuario.
 *    - Si tiene éxito:
 *        - Convierte la respuesta a DTO.
 *        - Actualiza la sesión guardada.
 *
 * 4. getLoggedUser():
 *    - Devuelve el usuario actualmente guardado en sesión.
 *
 * 5. logout():
 *    - Elimina la información del usuario de la sesión.
 *
 * Uso de Result:
 * - Permite manejar de forma segura tanto el éxito como los errores.
 * - Evita el uso de valores nulos y mejora el control de errores.
 *
 * SessionManager:
 * - Se utiliza para guardar y recuperar el usuario autenticado.
 * - Permite mantener la sesión incluso después de cerrar la app.
 */