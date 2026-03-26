package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.session.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID

class UsersInfoRepo(context: Context) {

    private val session = DataStoreManager(context)
    private val api = RetrofitModule.userInfoApi

    // Flujo que emite el usuario guardado en DataStore
    val loggedUserFlow: Flow<UsersInfoDTO?> = session.userFlow

    // Obtener usuario por ID desde la API
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

    // Registro de usuario y guardado en DataStore
    suspend fun register(
        email: String,
        name: String,
        pass: String,
        lastName: String,
        country: String
    ): Result<UsersInfoDTO> = withContext(Dispatchers.IO) {
        try {
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
                var registeredUser = response.body()!!.toDTO()

                // Generar ID temporal si API no devuelve
                if (registeredUser.id.isEmpty()) {
                    registeredUser = registeredUser.copy(id = UUID.randomUUID().toString())
                }

                // Guardar usuario en DataStore
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

    // Actualizar usuario en API y DataStore
    suspend fun update(dto: UsersInfoDTO): Result<UsersInfoDTO> = withContext(Dispatchers.IO) {
        try {
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

    // Cerrar sesión
    suspend fun logout() = withContext(Dispatchers.IO) {
        session.logout()
    }
}