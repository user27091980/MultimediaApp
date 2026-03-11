package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio que obtiene las bandas desde la API usando Retrofit.
 * Incluye todos los campos del JSON: albumImages, albumLinks, headerLink, imageBand.
 */
class BandsRepoRetrofit(context: Context) {

    private val api: ApiService = ApiService.create()

    // --- CRUD básico usando Retrofit ---

    suspend fun getAllBands(): List<BandDTO> = withContext(Dispatchers.IO) {
        val response = api.getAllBands()
        if (response.isSuccessful) response.body() ?: emptyList()
        else emptyList()
    }

    suspend fun getBandById(id: String): BandDTO? = withContext(Dispatchers.IO) {
        val response = api.getBandById(id)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun getBandByName(name: String): BandDTO? = withContext(Dispatchers.IO) {
        val response = api.getBandByName(name)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun getBandsByStyle(style: String): List<BandDTO> = withContext(Dispatchers.IO) {
        val response = api.getBandsByStyle(style)
        if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }

    suspend fun getBandsByLabel(label: String): List<BandDTO> = withContext(Dispatchers.IO) {
        val response = api.getBandsByLabel(label)
        if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }

    // --- Métodos adicionales para datos completos de la banda ---

    suspend fun getAlbumImages(id: String): List<String> = withContext(Dispatchers.IO) {
        val response = api.getAlbumImages(id)
        if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }

    suspend fun getAlbumLinks(id: String): List<String> = withContext(Dispatchers.IO) {
        val response = api.getAlbumLinks(id)
        if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }

    suspend fun getHeaderLink(id: String): String? = withContext(Dispatchers.IO) {
        val response = api.getHeaderLink(id)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun getImageBand(id: String): String? = withContext(Dispatchers.IO) {
        val response = api.getImageBand(id)
        if (response.isSuccessful) response.body() else null
    }
}