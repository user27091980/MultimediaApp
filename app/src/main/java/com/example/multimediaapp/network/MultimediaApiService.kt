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

    //banda
    @GET("json/band/{id}")
    suspend fun getBandId(@Path("id") id: Int): Response<BandDTO>

    @GET("json/nombre/{nombre}")
    suspend fun getBandName(@Path("nombre") name: String): Response<BandDTO>

    @GET("json/texto/{texto}")
    suspend fun getBandTextInfo(@Path("texto") textInfo: String): Response<BandDTO>

    @GET("json/imagenes/{id}")
    suspend fun getBandPic(@Path("cabecera") headerImage: String): Response<BandDTO>

    //para tags
    @GET("json/estilo/{estilo}")
    suspend fun getStyle(@Path("estilo") style: String): Response<BandDTO>

    @GET("json/discografica/{discografica}")
    suspend fun getDiscog(@Path("discografica") recordLabel: String): Response<BandDTO>

    @GET("json/componentes/{componentes}")
    suspend fun getComponentes(@Path("componentes") components: String): Response<BandDTO>

    @GET("json/discografia/{discografia}")
    suspend fun getDiscography(@Path("discografia") discography: List<String>): Response<BandDTO>

    //cabecera foto página de los grupos
    @GET("json/cabecera/{cabecera}")
    suspend fun getPicHead(@Path("cabecera") picHead: String): Response<BandDTO>

    //para el lazy row de las imágenes de la página de cada grupo
    @GET("json/discos/{discos}")
    suspend fun getDiscograph   (@Path("discos") imageAlbums: List<String>): Response<BandDTO>


}