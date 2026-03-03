package com.example.multimediaapp.network


import com.example.multimediaapp.model.BandDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/*
@Path → se usa para reemplazar partes de la URL ({id}).
@Query → se usa para parámetros de consulta (?id=...).
 */
interface MultimediaApiService {

    //banda
    @GET("json/band/{id}")
    suspend fun getBandId(@Query("id") id: String): Response<BandDTO>

    @GET("json/nombre/{nombre}")
    suspend fun getBandName(@Query("nombre") name: String): Response<BandDTO>

    @GET("json/texto/{texto}")
    suspend fun getBandTextInfo(@Query("texto") textInfo: String): Response<BandDTO>

    @GET("json/imagenes/{id}")
    suspend fun getHeaderPic(@Query("cabecera") headerImage: String): Response<BandDTO>
    //para tags
    @GET("json/estilo/{estilo}")
    suspend fun getStyle(@Query("estilo") style: String): Response<BandDTO>

    @GET("json/discografica/{discografica}")
    suspend fun getDiscography(@Query("discografica") recordLabel: String): Response<BandDTO>

    @GET("json/componentes/{componentes}")
    suspend fun getComponents(@Query("componentes") components: String): Response<BandDTO>

    @GET("json/discografia/{discografia}")
    suspend fun getDiscography(@Query("discografia") discography: List<String>): Response<BandDTO>

    //cabecera foto página de los grupos
    @GET("json/cabecera/{cabecera}")
    suspend fun getHeaderImage(@Query("cabecera") headerImage: String): Response<BandDTO>

    //para el lazy row de las imágenes de la página de cada grupo
    @GET("json/discos/{discos}")
    suspend fun getAlbumImages  (@Query("discos") albumImages: List<String>): Response<BandDTO>


}