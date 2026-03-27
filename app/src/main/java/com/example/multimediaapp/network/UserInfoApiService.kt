package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.UsersInfoEntity
import com.example.multimediaapp.model.RegisterRequestDTO
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * UserInfoApiService:
 *
 * Interfaz que define los endpoints relacionados con la gestión de usuarios.
 *
 * Responsabilidades:
 * - Obtener información de todos los usuarios o de un usuario específico.
 * - Registrar nuevos usuarios en el backend.
 * - Actualizar datos de usuarios existentes.
 *
 * Notas:
 * - Usamos `Response<UsersInfoEntity>` porque Retrofit devuelve el body completo.
 * - Los métodos usan DTOs o Entities según convenga para separar capas de dominio y datos.
 * - Las rutas son consistentes con el prefijo `json/userInfo`.
 *
 * ===Endpoints===
 *
 * 1. getUsersInfo()
 *    - Método GET
 *    - Devuelve la lista completa de usuarios
 *    - Response<List<UsersInfoEntity>>
 *
 * 2. getUser(id: String)
 *    - Método GET con parámetro path
 *    - Devuelve un usuario específico por su ID
 *    - Response<UsersInfoEntity>
 *
 * 3. registerUser(request: RegisterRequestDTO)
 *    - Método POST
 *    - Recibe DTO con los datos de registro
 *    - Devuelve la entidad registrada
 *
 * 4. updateUserInfo(id: String, user: UsersInfoDTO)
 *    - Método PUT
 *    - Actualiza un usuario existente mediante su ID
 *    - Devuelve la entidad actualizada
 *
 * ===Notas adicionales===
 * - `@Body` se usa para enviar el objeto JSON en el cuerpo del request.
 * - `@Path` indica que el parámetro se inserta directamente en la URL.
 * - Retrofit maneja automáticamente la serialización/deserialización.
 */
interface UserInfoApiService {

    @GET("json/auth/userInfo")
    suspend fun getUsersInfo(): Response<List<UsersInfoEntity>>

    @GET("json/userInfo/{id}")
    suspend fun getUser(@Path("id") id: String): Response<UsersInfoEntity>

    @POST("json/userInfo/register")
    suspend fun registerUser(
        @Body request: RegisterRequestDTO
    ): Response<UsersInfoEntity>

    @PUT("json/userInfo/{id}")
    suspend fun updateUserInfo(
        @Path("id") id: String,
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>
}