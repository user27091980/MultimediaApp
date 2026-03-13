package com.example.multimediaapp.retrofit

//import com.example.multimediaapp.network.ApiService
import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.network.MainApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    // La base debe ser solo el dominio y puerto. El resto va en el ApiService.
    private const val BASE_URL = "http://10.0.2.2:5131/"

    // Usamos la función create() que ya definiste en tu companion object de MainApiService
    val mainApi: MainApiService by lazy {
        MainApiService.create()
    }

    val bandApi: BandApiService by lazy {
        BandApiService.create()
    }
}