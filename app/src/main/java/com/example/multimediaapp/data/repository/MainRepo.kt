package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.ApiService

interface IMainRepo {
    suspend fun getBands(): List<MainDTO>
    suspend fun getBandById(id: String): MainDTO?
    suspend fun createBand(band: MainDTO): MainDTO?
    suspend fun updateBand(id: String, band: MainDTO): MainDTO?
    suspend fun deleteBand(id: String): Boolean
}

class MainRepo(private val apiService: ApiService) : IMainRepo {

    override suspend fun getBands(): List<MainDTO> {
        val response = apiService.getMainBands() // Tu endpoint de la pantalla principal
        return if (response.isSuccessful) {
            // AQUÍ ES DONDE SE CONSTRUYE LA URL COMPLETA
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getBandById(id: String): MainDTO? {
        val response = apiService.getMainBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun createBand(band: MainDTO): MainDTO? {
        val response = apiService.createMainBand(band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun updateBand(id: String, band: MainDTO): MainDTO? {
        val response = apiService.updateMainBand(id, band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun deleteBand(id: String): Boolean {
        val response = apiService.deleteMainBand(id)
        return response.isSuccessful
    }
}