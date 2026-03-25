package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.LoginApiService
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.network.UserInfoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Módulo centralizado de Retrofit.
 * Mantiene un singleton de Retrofit y crea instancias de los servicios de forma perezosa.
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
/*
 * Este archivo define el módulo central de Retrofit (RetrofitModule),
 * encargado de crear y proporcionar instancias de los servicios de API.
 *
 * Se implementa como un object (singleton) para asegurar que:
 * - Solo exista una instancia de Retrofit en toda la aplicación.
 * - Se optimicen los recursos y el rendimiento.
 *
 * BASE_URL:
 * - Dirección base del backend.
 * - "10.0.2.2" permite acceder al localhost del PC desde el emulador Android.
 *
 * RETROFIT:
 * - Librería utilizada para realizar peticiones HTTP.
 * - Convierte automáticamente JSON en objetos Kotlin usando Gson.
 *
 * by lazy:
 * - Se utiliza para inicializar las instancias solo cuando se necesitan.
 * - Esto mejora el rendimiento y evita crear objetos innecesarios al inicio.
 *
 * INSTANCIA RETROFIT:
 * - Se crea una única vez con:
 *   - baseUrl: URL del servidor
 *   - addConverterFactory: convierte JSON a objetos Kotlin
 *
 * SERVICIOS DE API:
 *
 * Cada servicio representa un conjunto de endpoints de la API:
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
 *   Servicio para gestión de información de usuarios.
 *
 * Cada servicio se crea utilizando:
 * retrofit.create(Servicio::class.java)
 *
 * VENTAJAS DE ESTE MÓDULO:
 * - Centraliza la configuración de red
 * - Evita duplicación de código
 * - Facilita el mantenimiento
 * - Permite cambiar el backend fácilmente desde un solo lugar
 *
 * Este módulo actúa como punto de entrada para todas las llamadas
 * a la API dentro de la aplicación.
 */