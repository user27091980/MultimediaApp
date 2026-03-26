package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.data.datastore.DataStoreManager

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import kotlinx.coroutines.flow.first

class UsersInfoRepo(context: Context) {

    private val api = RetrofitModule.userInfoApi
    private val dataStore = DataStoreManager(context) // corregido: DataStoreManager

    /**
     * Obtiene la información de un usuario por su ID desde la API.
     */
    suspend fun read(id: String): Result<UsersInfoDTO?> {
        return try {
            val response = api.getUserInfoById(id)
            if (response.isSuccessful) {
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
     * Guarda usuario en DataStore si el registro fue exitoso.
     */
    suspend fun register(
        email: String,
        name: String,
        pass: String,
        lastName: String,
        country: String
    ): Result<UsersInfoDTO> {
        return try {
            val dto = UsersInfoDTO(
                id = "",
                email = email,
                name = name,
                pass = pass,
                lastName = lastName,
                country = country
            )
            val response = api.registerUser(dto)
            if (response.isSuccessful && response.body() != null) {
                val registeredUser = response.body()!!.toDTO()
                // Guardar en DataStore (solo email y name)
                dataStore.saveUser(
                    name = registeredUser.name,
                    email = registeredUser.email
                )
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
     * Actualiza el perfil del usuario en la API y DataStore
     */
    suspend fun update(dto: UsersInfoDTO): Result<UsersInfoDTO> {
        return try {
            val response = api.updateUserInfo(dto.email, dto)
            if (response.isSuccessful && response.body() != null) {
                val updatedUser = response.body()!!.toDTO()
                // Actualizar datos en DataStore (solo email y name)
                dataStore.saveUser(
                    name = updatedUser.name,
                    email = updatedUser.email
                )
                Result.success(updatedUser)
            } else {
                Result.failure(Exception("No se pudo actualizar el perfil"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Devuelve el usuario actualmente logueado desde DataStore
     */
    suspend fun getLoggedUser(): UsersInfoDTO? {
        val name = dataStore.getName.first()   // suspend
        val email = dataStore.getEmail.first() // suspend
        return if (email.isNotBlank() && name.isNotBlank()) {
            UsersInfoDTO(
                id = "",
                name = name,
                email = email,
                pass = "", // no guardamos password
                lastName = "",
                country = ""
            )
        } else null
    }

    /**
     * Logout seguro: limpia DataStore
     */
    suspend fun logout() {
        dataStore.logout()
    }
}