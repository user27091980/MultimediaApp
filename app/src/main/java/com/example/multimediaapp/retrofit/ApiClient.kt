package com.example.multimediaapp.retrofit

//import com.example.multimediaapp.network.ApiService
import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.MainApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val BASE_URL = "http://10.0.2.2:5131/"

    // 1. Creamos una única instancia de Retrofit para toda la app
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 2. Usamos esa instancia para crear cada servicio de forma perezosa (lazy)
    val mainApi: MainApiService by lazy {
        retrofit.create(MainApiService::class.java)
    }

    val bandApi: BandApiService by lazy {
        retrofit.create(BandApiService::class.java)
    }
}