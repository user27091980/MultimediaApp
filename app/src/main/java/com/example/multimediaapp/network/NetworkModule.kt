package com.example.multimediaapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton que centraliza la configuración
 * de Retrofit y el cliente HTTP.
 *
 * Se usa object para que exista una única instancia
 * en toda la aplicación.
 */

object NetworkModule {
    /**
     * URL base del backend.
     *
     * 10.0.2.2 se usa en Android Emulator
     * para apuntar al localhost del PC.
     */
    public const val BASE_URL = "http://10.0.2.2:5131/"

    /**
     * Interceptor de logging.
     *
     * Permite ver en Logcat:
     * - Requests
     * - Responses
     *
     * BASIC → solo muestra método, URL y código respuesta.
     * BODY → muestra todo el cuerpo (útil en desarrollo).
     */
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    /**
     * Cliente HTTP personalizado.
     *
     * Aquí se pueden agregar:
     * - Interceptores
     * - Headers
     * - Timeouts
     * - Autenticación
     */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /**
     * Instancia principal de Retrofit.
     *
     * - baseUrl → dirección del backend
     * - client → cliente HTTP configurado
     * - converterFactory → convierte JSON a objetos Kotlin
     */
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Servicio API generado por Retrofit.
     *
     * Aquí se definen los endpoints en la interfaz
     *
     */
    val mainApi: MainApiService =
        retrofit.create(MainApiService::class.java)

    val bandApi: BandApiService =
        retrofit.create(BandApiService::class.java)

    val loginApi: LoginApiService =
        retrofit.create(LoginApiService::class.java)

    val userInfoApi: UserInfoApiService =
        retrofit.create(UserInfoApiService::class.java)
}
/*
 * Este archivo define el módulo de red (NetworkModule), que centraliza
 * toda la configuración relacionada con Retrofit y el cliente HTTP.
 *
 * Se implementa como un object (singleton) para garantizar que exista
 * una única instancia en toda la aplicación, evitando duplicaciones
 * y mejorando el rendimiento.
 *
 * BASE_URL:
 * - Es la URL base del backend.
 * - "10.0.2.2" permite acceder al servidor local desde el emulador Android.
 *
 * LOGGING (HttpLoggingInterceptor):
 * - Permite registrar en Logcat las peticiones HTTP.
 * - Ayuda en desarrollo y depuración.
 * - Nivel BASIC:
 *   - Muestra método HTTP, URL y código de respuesta.
 *   - Puede configurarse a BODY para ver el contenido completo.
 *
 * OkHttpClient:
 * - Cliente HTTP utilizado por Retrofit.
 * - Permite añadir configuraciones como:
 *   - Interceptores (logging, autenticación)
 *   - Headers personalizados
 *   - Timeouts
 *
 * Retrofit:
 * - Librería encargada de realizar llamadas a la API.
 * - Se configura con:
 *   - baseUrl: dirección del servidor
 *   - client: cliente HTTP personalizado
 *   - converterFactory: convierte JSON a objetos Kotlin (Gson)
 *
 * Servicios API:
 *
 * - mainApi:
 *   Servicio para la pantalla principal (Main).
 *
 * - bandApi:
 *   Servicio para operaciones relacionadas con bandas.
 *
 * - loginApi:
 *   Servicio para autenticación y login.
 *
 * - userInfoApi:
 *   Servicio para obtener y gestionar información del usuario.
 *
 * IMPORTANTE:
 * - Centralizar la configuración evita duplicar código.
 * - Facilita el mantenimiento y escalabilidad del proyecto.
 * - Permite modificar la configuración de red desde un solo lugar.
 *
 * Este módulo es fundamental en la arquitectura de la aplicación,
 * ya que actúa como punto de entrada para todas las comunicaciones
 * con el backend.
 */