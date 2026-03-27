package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.LoginRequestDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * LoginApiService:
 *
 * Interfaz de Retrofit que define los **endpoints de autenticación y usuario**.
 *
 * Funciones principales:
 * - Obtener información de un usuario por su ID
 * - Realizar login enviando credenciales
 *
 * Uso de Retrofit:
 * - `@GET` y `@POST` mapean llamadas HTTP automáticamente.
 * - `@Path` reemplaza parámetros en la URL.
 * - `@Body` convierte un objeto Kotlin en JSON para la petición POST.
 * - `Response<T>` permite manejar códigos HTTP y respuestas nulas de forma segura.
 */
interface LoginApiService {

    /** Obtiene la información de un usuario por su ID */
    @GET("json/user/{id}")
    suspend fun getUser(@Path("id") id: String): Response<LoginDTO>

    /** Realiza login enviando usuario/email y contraseña */
    @POST("auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequestDTO): Response<LoginDTO>

    companion object {
        /** URL base de la API (para emulador de Android usar 10.0.2.2) */
        private const val BASE_URL = "http://10.0.2.2:5131/"

        /**
         * Crea una instancia de LoginApiService usando Retrofit
         *
         * - `GsonConverterFactory` convierte JSON ↔ objetos Kotlin automáticamente
         * - Se puede reemplazar por Moshi o Kotlinx.serialization si se desea
         */
        fun create(): LoginApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LoginApiService::class.java)
        }
    }
}

/**
 * NOTAS:
 *
 * 1. Todas las funciones son `suspend` → se ejecutan dentro de coroutines.
 * 2. `Response<T>` → permite verificar `isSuccessful`, manejar errores y obtener código HTTP.
 * 3. `@Path` → inyecta valores en la URL dinámica.
 * 4. `@Body` → serializa objetos automáticamente para POST.
 * 5. `companion object` → patrón común para crear instancias de Retrofit sin repetir configuración.
 */