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

    /**
     * Obtiene información completa de un usuario por su ID.
     *
     * @param userId ID del usuario.
     * @return DTO con la información del usuario.
     * @throws Exception si ocurre error de red o el servidor devuelve una respuesta inválida.
     */
    suspend fun getUserInfo(userId: String): UsersInfoDTO

    /**
     * Registra un nuevo usuario en el servidor.
     *
     * @param user Nombre de usuario.
     * @param email Email del usuario.
     * @param name Nombre.
     * @param pass Contraseña.
     * @param country País.
     * @param lastName Apellido.
     * @return [Result] con los datos del usuario registrado en caso de éxito,
     *         o con una excepción en caso de fallo.
     */
    suspend fun register(

        email: String,
        name: String,
        pass: String,
        country: String,
        lastName: String
    ): Result<UsersInfoDTO>
}

/**
 * Implementación de [IUserInfoRepo] usando Retrofit para comunicarse con [UserInfoApiService].
 *
 * Cada método se ejecuta en un contexto de I/O mediante corutinas,
 * maneja errores de red y convierte las entidades recibidas a DTOs
 * para ser usados en la capa de presentación.
 *
 * @property api Servicio Retrofit que proporciona los endpoints de usuario.
 */
class UserInfoRepo(private val api: UserInfoApiService) {

    /**
     * Registra un nuevo usuario en el servidor.
     *
     * Se crea un [RegisterRequestDTO] con los datos del usuario,
     * se envía a la API y se convierte la respuesta a [UsersInfoDTO].
     *
     * Maneja:
     * - Errores de red (IOException)
     * - Respuestas vacías del servidor
     * - Otros errores desconocidos
     */
    suspend fun register(

        email: String,
        name: String,
        passwd: String,
        country: String,
        lastName: String
    ): Result<UsersInfoDTO> = withContext(Dispatchers.IO) {
        try {
            val request = RegisterRequestDTO(email, name, passwd, country, lastName)
            val response = api.registerUser(request)

            if (response.isSuccessful) {
                val body = response.body() ?: throw Exception("Respuesta vacía del servidor")
                Result.success(body.toDTO())
            } else {
                Result.failure(Exception("Error al registrar usuario: ${response.code()}"))
            }

        } catch (e: IOException) {
            Result.failure(Exception("Error de red", e))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error desconocido", e))
        }
    }

    /**
     * Obtiene la información completa de un usuario por su ID.
     *
     * Llama al endpoint correspondiente, verifica si la respuesta fue exitosa
     * y convierte la entidad recibida a [UsersInfoDTO].
     *
     * @param userId ID del usuario a obtener.
     * @return [UsersInfoDTO] con la información del usuario.
     * @throws Exception si ocurre un error de red o la respuesta del servidor es inválida.
     */
    suspend fun getUser(userId: String): UsersInfoDTO = withContext(Dispatchers.IO) {
        try {
            val response = api.getUser(userId)
            if (response.isSuccessful) {
                val body: UsersInfoEntity? = response.body()
                body?.toDTO() ?: throw Exception("Respuesta vacía del servidor")
            } else {
                throw Exception("Error al obtener información del usuario: ${response.code()}")
            }
        } catch (e: IOException) {
            throw Exception("Error de red: Verifica tu conexión", e)
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error desconocido", e)
        }
    }
}