package com.example.multimediaapp.network


// DTO que representa los datos de una banda
import com.example.multimediaapp.model.BandDTO
// Retrofit Response para manejar respuestas HTTP
import retrofit2.Response
// Anotaciones de Retrofit para definir métodos HTTP
import retrofit2.http.GET
import retrofit2.http.Query

/*
🔹 Notas sobre Retrofit:

@Path → Se usa para reemplazar partes variables de la URL, por ejemplo:
    @GET("json/band/{id}") → {id} se reemplaza con un valor en tiempo de ejecución

@Query → Se usa para agregar parámetros de consulta a la URL, ejemplo:
    /json/band?id=123
*/


/**
 * 🔹 Interfaz que define los endpoints de la API
 * Usada por Retrofit para hacer las llamadas HTTP
 */
interface MultimediaApiService {

    // 🔹 Obtener información de una banda por ID
    @GET("json/band/{id}")
    // parámetro de consulta (?id=...)
    suspend fun getBandId(@Query("id") id: String): Response<BandDTO>
    //información de una banda por nombre
    @GET("json/nombre/{nombre}")
    suspend fun getBandName(@Query("nombre") name: String): Response<BandDTO>
    //información de texto de un abanda
    @GET("json/texto/{texto}")
    suspend fun getBandTextInfo(@Query("texto") textInfo: String): Response<BandDTO>
    //obtener imagen de la cabecera
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

    //para el lazy row de las imágenes de la página de cada grupo
    @GET("json/discos/{discos}")
    suspend fun getAlbumImages  (@Query("discos") albumImages: List<String>): Response<BandDTO>


}