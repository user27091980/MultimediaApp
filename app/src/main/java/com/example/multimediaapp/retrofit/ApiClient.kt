package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.LoginApiService
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.network.UserInfoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RetrofitModule:
 *
 * Objeto singleton que centraliza la configuración de Retrofit
 * y la creación de los servicios API.
 *
 * Responsabilidades:
 * 1. Mantener una única instancia de Retrofit en toda la aplicación.
 * 2. Crear instancias perezosas (lazy) de los servicios de API:
 *    - mainApi
 *    - bandApi
 *    - loginApi
 *    - userInfoApi
 *
 * Notas de implementación:
 * - `BASE_URL` apunta al backend. En Android Emulator se usa 10.0.2.2 para localhost.
 * - `GsonConverterFactory` se usa para serializar/deserializar JSON automáticamente.
 * - `by lazy` asegura que Retrofit y los servicios se inicialicen solo cuando se usen.
 * - Cada API service se crea a partir de la misma instancia de Retrofit,
 *   garantizando consistencia y ahorro de recursos.
 *
 * ===Ventajas===
 * - Singleton: evita múltiples instancias de Retrofit.
 * - Centralización: todas las APIs usan la misma configuración.
 * - Escalabilidad: fácil agregar nuevos servicios de API sin duplicar código.
 *
 * Ejemplo de uso:
 * val bandRepo = BandsRepo(RetrofitModule.bandApi)
 * val loginRepo = LoginRepo(RetrofitModule.loginApi)
 */
object RetrofitModule {

    private const val BASE_URL = "http://10.0.2.2:5131/"

    // Instancia única de Retrofit para toda la app
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicios de API creados con la misma instancia de Retrofit
    val mainApi: MainApiService by lazy {
        retrofit.create(MainApiService::class.java)
    }

    val bandApi: BandApiService by lazy {
        retrofit.create(BandApiService::class.java)
    }

    val loginApi: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }

    val userInfoApi: UserInfoApiService by lazy {
        retrofit.create(UserInfoApiService::class.java)
    }
}