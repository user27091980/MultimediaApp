package com.example.multimediaapp.network

// Modelo de datos que representa la respuesta del servidor
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.model.UsersInfoDTO
// Clase wrapper que contiene:
// - body()
// - isSuccessful
// - errorBody()
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
// Anotaciones de Retrofit para definir endpoints HTTP
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/*
@Path → se usa para reemplazar partes de la URL ({id}).
@Query → se usa para parámetros de consulta (?id=...).
 */
interface ApiService {
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
    @GET("main/{id}/imageBand")
    suspend fun getImageBand(@Path("id") id: String): Response<String>
    // Endpoint que devuelve todas las bandas
    @GET("mainbands")
    suspend fun getAllMain(): Response<List<MainDTO>>

    // Endpoint que devuelve una banda por ID
    @GET("mainbands/{id}")
    suspend fun getmainById(@Path("id") id: String): Response<MainDTO>
    // Usuarios
    @GET("users")
    suspend fun getAllUsers(): Response<List<UsersInfoDTO>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<UsersInfoDTO>

    @POST("users")
    suspend fun createUser(@Body user: UsersInfoDTO): Response<UsersInfoDTO>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: UsersInfoDTO): Response<UsersInfoDTO>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>

    // Login
    @POST("login")
    suspend fun login(@Body login: LoginDTO): Response<UsersInfoDTO>
    @GET("main/{id}")
    suspend fun updateBand(@Path("id") id: String, band: MainDTO): Response <MainDTO>
    @DELETE("main/{id}")
    suspend fun deleteBand(@Path("id") id:String): Response <MainDTO>
    @PUT("main/{id}")
    fun createBand(@Path("id") id: String, band: MainDTO): Response <MainDTO>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}



