package com.example.multimediaapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * NetworkModule:
 *
 * Objeto singleton que centraliza la configuración de **Retrofit** y el cliente HTTP (OkHttp).
 *
 * Responsabilidades:
 * - Mantener una única instancia de Retrofit en toda la aplicación.
 * - Configurar cliente HTTP con logging, interceptores, headers, timeouts, etc.
 * - Crear instancias de los servicios API (MainApiService, BandApiService, LoginApiService, UserInfoApiService).
 *
 * Notas:
 * - Se usa `object` → garantiza que solo exista una instancia global.
 * - Se usa `10.0.2.2` en Android Emulator para apuntar al localhost del PC.
 * - Se puede cambiar `HttpLoggingInterceptor.Level` a BODY para debugging detallado.
 */
object NetworkModule {

    /** URL base del backend */
    const val BASE_URL = "http://10.0.2.2:5131/"

    /** Interceptor de logging para ver requests/responses en Logcat */
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    /** Cliente HTTP personalizado */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /** Instancia principal de Retrofit */
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /** Servicios API creados a partir de Retrofit */
    val mainApi: MainApiService = retrofit.create(MainApiService::class.java)
    val bandApi: BandApiService = retrofit.create(BandApiService::class.java)
    val loginApi: LoginApiService = retrofit.create(LoginApiService::class.java)
    val userInfoApi: UserInfoApiService = retrofit.create(UserInfoApiService::class.java)
}

/**
 * ===APUNTES ADICIONALES===
 *
 * 1. LoggingInterceptor:
 *    - BASIC → solo método, URL y código de respuesta
 *    - BODY → incluye cuerpo de request/response (útil para desarrollo)
 *
 * 2. OkHttpClient:
 *    - Permite agregar autenticación, headers, retries, timeouts.
 *
 * 3. Retrofit:
 *    - Convierte automáticamente JSON a objetos Kotlin mediante Gson.
 *    - Facilita la definición de endpoints en interfaces.
 *
 * 4. Servicios API:
 *    - `mainApi` → endpoints de MainScreen
 *    - `bandApi` → endpoints de bandas
 *    - `loginApi` → endpoints de login
 *    - `userInfoApi` → endpoints de información de usuario
 */