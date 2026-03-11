package com.example.multimediaapp.network

// Modelo de datos que representa la respuesta del servidor
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UsersInfoDTO
// Clase wrapper que contiene:
// - body()
// - isSuccessful
// - errorBody()
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// Anotaciones de Retrofit para definir endpoints HTTP
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/*
@Path → se usa para reemplazar partes de la URL ({id}).
@Query → se usa para parámetros de consulta (?id=...).
 */
interface ApiBandsService {
    // Obtener todas las bandas
    @GET("bands")
    suspend fun getAllBands(): Response<List<BandDTO>>

    // Obtener banda por ID
    @GET("bands/{id}")
    suspend fun getBandById(@Path("id") id: String): Response<BandDTO>

    // Buscar banda por nombre
    @GET("bands/search")
    suspend fun getBandByName(@Query("name") name: String): Response<BandDTO>

    // Obtener bandas por estilo
    @GET("bands/style/{style}")
    suspend fun getBandsByStyle(@Path("style") style: String): Response<List<BandDTO>>

    // Obtener bandas por discográfica
    @GET("bands/label/{label}")
    suspend fun getBandsByLabel(@Path("label") label: String): Response<List<BandDTO>>

    // Obtener imágenes del álbum de una banda
    @GET("bands/{id}/albumImages")
    suspend fun getAlbumImages(@Path("id") id: String): Response<List<String>>

    // Obtener enlaces de los álbumes de una banda
    @GET("bands/{id}/albumLinks")
    suspend fun getAlbumLinks(@Path("id") id: String): Response<List<String>>

    // Obtener el enlace principal (header) de la banda
    @GET("bands/{id}/headerLink")
    suspend fun getHeaderLink(@Path("id") id: String): Response<String>

    // Obtener la imagen principal de la banda
    @GET("bands/{id}/imageBand")
    suspend fun getImageBand(@Path("id") id: String): Response<String>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"
        fun create(): ApiBandsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiBandsService::class.java)
        }
    }
}



