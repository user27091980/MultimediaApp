package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.LoginApiService
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.network.UserInfoApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitModule:
 *
 * Singleton que centraliza la configuración de red de la app.
 *
 * Incluye:
 * - Configuración de Retrofit
 * - Cliente HTTP con logging
 * - Timeouts para evitar bloqueos
 * - Creación de APIs
 */
object RetrofitModule {

    /** URL base de la API */
    private const val BASE_URL = "http://10.0.2.2:5131/"

    /**
     * Interceptor para ver logs en Logcat
     * MUY útil para debug (peticiones, respuestas, errores)
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Cliente HTTP personalizado
     */
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Instancia única de Retrofit
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** APIs */

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