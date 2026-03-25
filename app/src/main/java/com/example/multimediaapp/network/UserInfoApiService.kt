package com.example.multimediaapp.network

import com.example.multimediaapp.data.entity.UsersInfoEntity
import com.example.multimediaapp.model.UsersInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * API para gestionar la información de los usuarios.
 * Mantiene coherencia de rutas y usa DTOs en el body para seguridad.
 */
interface UserInfoApiService {

    // Obtener lista completa de usuarios
    @GET("json/auth/userInfo") // Quitamos la barra final innecesaria
    suspend fun getUsersInfo(): Response<List<UsersInfoEntity>>

    // Obtener un usuario específico por ID
    @GET("json/userInfo/{id}")
    suspend fun getUserInfoById(@Path("id") id: String): Response<UsersInfoEntity>

    // Registrar un nuevo usuario
    @POST("json/userInfo/register")
    suspend fun registerUser(
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>

    // Actualizar un usuario existente por ID
    @PUT("json/userInfo/{id}") // Unificamos el prefijo para que sea consistente
    suspend fun updateUserInfo(
        @Path("id") id: String,
        @Body user: UsersInfoDTO
    ): Response<UsersInfoEntity>
}
/*
 * Esta interfaz define los endpoints de la API relacionados con la gestión
 * de la información de los usuarios.
 *
 * Forma parte de la capa de red y utiliza Retrofit para realizar peticiones HTTP
 * al backend de forma estructurada y tipada.
 *
 * Todas las funciones son suspend, lo que permite ejecutar llamadas
 * de red de forma asíncrona mediante corrutinas sin bloquear la UI.
 *
 * RESPUESTA:
 * Todas las funciones devuelven Response<T>, lo que permite:
 * - Verificar si la petición fue exitosa (isSuccessful)
 * - Acceder al cuerpo de la respuesta (body())
 * - Manejar errores HTTP de forma controlada
 *
 * ENDPOINTS:
 *
 * 1. getUsersInfo():
 * - @GET("json/auth/userInfo")
 * - Obtiene la lista completa de usuarios del sistema.
 *
 * 2. getUserInfoById(id):
 * - @GET("json/userInfo/{id}")
 * - Obtiene la información de un usuario específico por su ID.
 * - Usa @Path para insertar el id dinámicamente en la URL.
 *
 * 3. registerUser(user):
 * - @POST("json/userInfo/register")
 * - Registra un nuevo usuario en el sistema.
 * - Recibe un objeto UsersInfoDTO en el cuerpo de la petición (@Body).
 *
 * 4. updateUserInfo(id, user):
 * - @PUT("json/userInfo/{id}")
 * - Actualiza la información de un usuario existente.
 * - Requiere el ID del usuario y un objeto UsersInfoDTO con los nuevos datos.
 *
 * MODELOS UTILIZADOS:
 *
 * - UsersInfoEntity:
 *   Representa los datos tal como los envía/recibe la API (capa de datos).
 *
 * - UsersInfoDTO:
 *   Representa los datos utilizados dentro de la aplicación (capa de dominio).
 *
 * USO DE DTO:
 * - Permite desacoplar la app del backend.
 * - Mejora la seguridad al controlar los datos que se envían.
 * - Facilita el mantenimiento y escalabilidad.
 *
 * ANOTACIONES:
 *
 * - @GET, @POST, @PUT:
 *   Definen el tipo de operación HTTP.
 *
 * - @Path:
 *   Permite insertar valores dinámicos en la URL.
 *
 * - @Body:
 *   Envía datos en el cuerpo de la petición.
 *
 * IMPORTANTE:
 * - Mantener coherencia en las rutas del backend.
 * - Asegurar que los endpoints coincidan con la API.
 * - Utilizar siempre DTOs para mantener la arquitectura limpia.
 *
 * Este servicio forma parte de la capa de red y es utilizado por los
 * repositorios para interactuar con el servidor.
 */