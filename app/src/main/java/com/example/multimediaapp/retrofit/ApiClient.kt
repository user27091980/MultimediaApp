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

class ApiClient(private val context: Context) {
    var retrofit: Retrofit? = null
    val baseUrl: String = "http://10.0.2.2:5097/api/" // Replace with your actual base URL

    // Get the Retrofit client instance
    fun getClient(): Retrofit {
        if (retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .addInterceptor(ForbiddenInterceptor())
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

class ForbiddenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 403) {
            CoroutineScope(Dispatchers.Main).launch {
                SessionEvents.emitForbidden()
            }
        }

        return response
    }
}

object SessionEvents {
    private val _forbidden = MutableSharedFlow<Unit>()
    val forbidden = _forbidden

    suspend fun emitForbidden() {
        _forbidden.emit(Unit)
    }
}

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = "1234" //In here get the token from where u storage it

        val request = if (!token.isNullOrBlank()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}