package com.example.multimediaapp.retrofit

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase que construye y proporciona la instancia de Retrofit.
 *
 * Recibe el Context porque el AuthInterceptor
 * podría necesitar acceder a almacenamiento local
 * (SharedPreferences, DataStore, etc.) para obtener el token.
 */
class ApiClient(private val context: Context) {
    // Instancia nullable de Retrofit (se inicializa una sola vez)
    var retrofit: Retrofit? = null

    /**
     * URL base del backend.
     * 10.0.2.2 apunta al localhost del PC desde el emulador.
     */
    val baseUrl: String = "http://10.0.2.2:5097/ApiGenerica/" // Replace with your actual base URL

    /**
     * Devuelve la instancia de Retrofit.
     * Si no existe, la crea.
     */
    fun getClient(): Retrofit {
        if (retrofit == null) {
            // Cliente HTTP con interceptores personalizados
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context)) // Añade token
                .addInterceptor(ForbiddenInterceptor())// Detecta 403
                .build()
            // Construcción de Retrofit
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

/**
 * Interceptor que detecta respuestas 403 (Forbidden).
 *
 * Si el servidor devuelve 403:
 * - Significa que el token no es válido
 * - O que el usuario no tiene permisos
 *
 * Entonces emitimos un evento global.
 */
class ForbiddenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        // Si el servidor devuelve 403
        if (response.code == 403) {
            // Lanzamos corrutina en el hilo principal
            CoroutineScope(Dispatchers.Main).launch {
                SessionEvents.emitForbidden()
            }
        }

        return response
    }
}

/**
 * Objeto que actúa como emisor global de eventos de sesión.
 *
 * Usa SharedFlow para notificar a la UI
 * cuando ocurre un 403.
 */
object SessionEvents {
    // Flujo compartido para emitir eventos
    private val _forbidden = MutableSharedFlow<Unit>()

    // Exponemos el flujo públicamente
    val forbidden = _forbidden

    /**
     * Función suspend que emite el evento.
     */
    suspend fun emitForbidden() {
        _forbidden.emit(Unit)
    }
}

/**
 * Interceptor que añade el header Authorization
 * a cada request si existe un token.
 */
class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // Aquí deberías obtener el token real
        // desde SharedPreferences o DataStore
        val token = "1234"

        val request = if (!token.isNullOrBlank()) {
            // Añadimos el header Bearer
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}