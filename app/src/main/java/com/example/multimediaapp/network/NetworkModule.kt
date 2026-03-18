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
