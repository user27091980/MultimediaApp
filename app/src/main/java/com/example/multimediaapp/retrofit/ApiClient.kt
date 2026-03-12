package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    // La base debe ser solo el dominio y puerto. El resto va en el ApiService.
    private const val BASE_URL = "http://10.0.2.2:5131/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}