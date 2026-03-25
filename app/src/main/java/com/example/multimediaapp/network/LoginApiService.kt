package com.example.multimediaapp.network

import com.example.multimediaapp.model.LoginDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApiService {


    @GET("json/user/{id}")
    suspend fun getUser(@Path("id",) id: String): Response<LoginDTO>

    // Para el LOGIN: Se suelen pasar como Query o en el Body de un POST
    // Si tu API .NET lo espera por URL:
    @POST("json/user/login")
    suspend fun loginUser(email: String, @Body login: String): Response<LoginDTO>
    companion object {
        private const val BASE_URL = "http://10.0.2.2:5131/"

        fun create(): LoginApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LoginApiService::class.java)
        }
    }
}
/*
 * Esta interfaz define los endpoints de la API relacionados con el login
 * y la obtención de información de usuario.
 *
 * Forma parte de la capa de red y utiliza Retrofit para realizar
 * las peticiones HTTP al servidor.
 *
 * Cada función representa una operación concreta contra la API.
 *
 * ENDPOINTS:
 *
 * 1. getUser(id):
 * - @GET("json/user/{id}")
 * - Obtiene un usuario por su identificador.
 * - Utiliza @Path para insertar el id dinámicamente en la URL.
 *
 * 2. loginUser(email, login):
 * - @POST("json/user/login")
 * - Realiza la autenticación del usuario.
 *
 * IMPORTANTE:
 * - El parámetro email no está anotado correctamente (debería usar @Query o @Body).
 * - El segundo parámetro usa @Body para enviar datos en el cuerpo de la petición.
 *
 * NOTA:
 * - Dependiendo del backend (.NET en este caso), el login puede requerir:
 *   - @Body con un objeto
 *   - o @Query con parámetros en la URL
 *
 * RESPUESTA:
 * - Todas las funciones devuelven Response<LoginDTO>
 * - Esto permite comprobar si la petición fue exitosa y acceder al contenido.
 *
 * COMPANION OBJECT:
 *
 * - Define la configuración de Retrofit.
 *
 * BASE_URL:
 * - "http://10.0.2.2:5131/"
 * - Esta dirección permite acceder al servidor local desde el emulador Android.
 *
 * create():
 * - Construye una instancia de Retrofit.
 * - Configura el convertidor Gson para serializar/deserializar JSON.
 * - Devuelve una implementación de LoginApiService lista para usar.
 *
 * IMPORTANTE:
 * - La configuración de Retrofit debe hacerse una sola vez (singleton idealmente).
 * - El BASE_URL debe coincidir con el backend.
 *
 * Este servicio es utilizado por el repositorio para separar la lógica de red
 * de la lógica de negocio.
 */