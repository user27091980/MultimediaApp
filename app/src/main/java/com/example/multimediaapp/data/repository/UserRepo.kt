package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.LoginDTO
import retrofit2.Response
import com.example.multimediaapp.model.UserDTO
import com.example.multimediaapp.network.UserApiService

class UserRepo(
    private val api: UserApiService
) {

    // --- REGISTER ---
    suspend fun register(
        email: String,
        pass: String,
        name: String,
        lastName: String,
        country: String
    ): Result<UserDTO> {
        return safeApiCall {
            val user = UserDTO(
                email = email,
                pass = pass,
                name = name,
                lastName = lastName,
                country = country
            )
            api.registerUser(user)
        }
    }

    // --- LOGIN (CORREGIDO) ---
    suspend fun login(
        email: String,
        pass: String
    ): Result<UserDTO> {
        return safeApiCall {
            val login = LoginDTO(
                email = email,
                pass = pass
            )
            api.loginUser(login)
        }
    }

    // --- GET USER BY ID ---
    suspend fun getUserById(id: String): Response<UserDTO> {
        return api.getUser(id)
    }

    // --- GENERIC API HANDLER ---
    private suspend fun safeApiCall(
        apiCall: suspend () -> Response<UserDTO>
    ): Result<UserDTO> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Respuesta vacía"))
                }
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}