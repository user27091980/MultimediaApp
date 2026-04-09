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
 * Interfaz de Retrofit que define los contratos de comunicación con el backend
 * para la gestión de datos de usuario y procesos de autenticación.
 *
 * Responsabilidades:
 * - Recuperar el perfil detallado de un usuario mediante su identificador único.
 * - Gestionar el alta de nuevos usuarios en el sistema a través del endpoint de registro.
 *
 * Métodos:
 * 1. getUser(id: String):
 *    - Realiza una petición GET a "json/user/{id}".
 *    - Se utiliza para cargar la información del perfil en la UI tras el login o registro.
 *    - Devuelve una [Response] que envuelve la entidad [UsersInfoEntity].
 *
 * 2. registerUser(request: RegisterRequestDTO):
 *    - Realiza una petición POST a "auth/register".
 *    - Envía los datos básicos (email, nombre, contraseña) encapsulados en [RegisterRequestDTO].
 *    - Tras el éxito, el servidor devuelve la entidad creada incluyendo su nuevo ID.
 *
 * Notas técnicas:
 * - Implementa el uso de 'suspend' para integrarse con Corrutinas de Kotlin,
 *   asegurando que las peticiones de red no bloqueen el hilo principal (UI).
 * - Utiliza anotaciones de Retrofit (@GET, @POST, @Path, @Body) para la
 *   configuración dinámica de las rutas y el cuerpo de las peticiones.
 */
interface UserInfoApiService {

    @GET("json/user/{id}")
    suspend fun getUser(@Path("id") id: String): Response<UsersInfoEntity>

    @POST("auth/register")
    suspend fun registerUser(
        @Body request: RegisterRequestDTO
    ): Response<UsersInfoEntity>

}