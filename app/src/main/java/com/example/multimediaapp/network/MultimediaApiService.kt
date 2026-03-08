package com.example.multimediaapp.network

// Modelo de datos que representa la respuesta del servidor
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.model.UsersDTO
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
interface MultimediaApiService {

    //obtener banda por Id
    @GET("json/band/{id}")
    suspend fun getBandId(@Path("id") id: String): Response<BandDTO>

    //obtener banda por nombre
    @GET("json/nombre/{nombre}")
    suspend fun getBandName(@Path("nombre") name: String): Response<BandDTO>

    //obtener información de la banda
    @GET("json/texto/{texto}")
    suspend fun getBandTextInfo(@Path("texto") textInfo: String): Response<BandDTO>

    //obtener imagen cabecera
    @GET("json/imagenes/{id}")
    suspend fun getHeaderPic(@Path("id") headerImage: String): Response<BandDTO>

    //para tags
    @GET("json/estilo/{estilo}")
    suspend fun getStyle(@Path("estilo") style: String): Response<BandDTO>

    @GET("json/discografica/{discografica}")
    suspend fun getDiscography(@Path("discografica") recordLabel: String): Response<BandDTO>

    @GET("json/componentes/{componentes}")
    suspend fun getComponents(@Path("componentes") components: String): Response<BandDTO>

    @GET("json/discografia/{discografia}")
    suspend fun getDiscography(@Path("discografia") discography: List<String>): Response<BandDTO>

    @GET("json/links/{links}")
    suspend fun getLinkss(@Path("enlaces") albumLinks: List<String>): Response<BandDTO>

    @GET("json/imagenes/main")
    suspend fun getImagesMain(): List<MainDTO>

    //para el lazy row de las imágenes de la página de cada grupo
    @GET("json/discos/{discos}")
    suspend fun getAlbumImages(@Path("discos") albumImages: List<String>): Response<BandDTO>

    @GET("json/users/{id}")
    suspend fun getUsers(@Path("id") userId: String): Response<UsersDTO>

    @PUT("json/users/{id}")
    suspend fun updateUser(@Path("id") userId: String): Response<UsersDTO>

    @GET("json/users/{id}")
    suspend fun getUsersInfo(@Path("id") userId: String): Response<UsersInfoDTO>


    companion object {

        private const val BASE_URL = "http://10.0.2.2:5131/"
        fun create(): MultimediaApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MultimediaApiService::class.java)
        }
    }
}