package com.example.multimediaapp.network


import com.example.multimediaapp.data.model.BandDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/*
@Path → se usa para reemplazar partes de la URL ({id}).
@Query → se usa para parámetros de consulta (?id=...).
 */
interface MultimediaApiService {

    // Obtener banda por id
    @GET("json/band/{id}")
    suspend fun getBandById(@Path("id") id: Int): Response<BandDTO>







}