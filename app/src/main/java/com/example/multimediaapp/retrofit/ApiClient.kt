package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.network.UserApiService
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

    val UserApi: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }


}