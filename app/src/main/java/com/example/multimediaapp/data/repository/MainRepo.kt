package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.MainApiService


interface IMainRepo {
    suspend fun getBands(): List<MainDTO>
    suspend fun getBandById(id: String): MainDTO?
    suspend fun createBand(band: MainDTO): MainDTO?
    suspend fun updateBand(id: String, band: MainDTO): MainDTO?
    suspend fun deleteBand(id: String): Boolean

    suspend fun getImages(id: String): MainDTO?

}

class MainRepo(private val mainApi: MainApiService) : IMainRepo {

    override suspend fun getBands(): List<MainDTO> {
        val response = mainApi.getMainBands() // Tu endpoint de la pantalla principal
        return if (response.isSuccessful) {
            // AQUÍ ES DONDE SE CONSTRUYE LA URL COMPLETA
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getBandById(id: String): MainDTO? {
        val response = mainApi.getMainBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun createBand(band: MainDTO): MainDTO? {
        val response = mainApi.createMainBand(band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun updateBand(id: String, band: MainDTO): MainDTO? {
        val response = mainApi.updateMainBand(id, band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    override suspend fun deleteBand(id: String): Boolean {
        val response = mainApi.deleteMainBand(id)
        return response.isSuccessful
    }

    override suspend fun getImages(id: String): MainDTO {
        val response = mainApi.getMainImages(id)
        if (response.isSuccessful) {
            return response.body()?.toDTO() ?: throw Exception("Cuerpo vacío")
        }
        throw Exception("Error al obtener imágenes")
    }

}