package com.example.multimediaapp.retrofit

import com.example.multimediaapp.network.ApiBandsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val BASE_URL = "http://10.0.2.2:5097/ApiGenerica/"

    val apiBandsService: ApiBandsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiBandsService::class.java)
    }

}