package com.example.multimediaapp.network

// Modelo de datos que representa la respuesta del servidor
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.MainDTO
// Clase wrapper que contiene:
// - body()
// - isSuccessful
// - errorBody()
import retrofit2.Response
// Anotaciones de Retrofit para definir endpoints HTTP
import retrofit2.http.GET
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

    @GET("json/imagenes/main")
    suspend fun getImagesMain(): List<MainDTO>

    //para el lazy row de las imágenes de la página de cada grupo
    @GET("json/discos/{discos}")
    suspend fun getAlbumImages(@Path("discos") albumImages: List<String>): Response<BandDTO>


}