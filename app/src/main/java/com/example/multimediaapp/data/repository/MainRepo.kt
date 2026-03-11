package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.MainEntity
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepo {

    private val api = ApiService.create()

    // =========================
    // READ
    // =========================
    suspend fun getAllBands(): List<MainDTO> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllBands()
            if (response.isSuccessful) {
                response.body()?.map { it.toDTO() } ?: emptyList()
            } else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getBandById(id: String): MainDTO? = withContext(Dispatchers.IO) {
        try {
            val response = api.getBandById(id)
            if (response.isSuccessful) response.body()?.toDTO() else null
        } catch (e: Exception) {
            null
        }
    }

    // =========================
    // CREATE
    // =========================
    suspend fun createBand(band: MainDTO): MainDTO? = withContext(Dispatchers.IO) {
        try {
            val entity = MainEntity(band.id, band.bandName, band.imageBand)
            val response = api.createBand(entity)
            if (response.isSuccessful) response.body()?.toDTO() else null
        } catch (e: Exception) {
            null
        }
    }

    // =========================
    // UPDATE
    // =========================
    suspend fun updateBand(band: MainDTO): MainDTO? = withContext(Dispatchers.IO) {
        try {
            val entity = MainEntity(band.id, band.bandName, band.imageBand)
            val response = api.updateBand(band.id, entity)
            if (response.isSuccessful) response.body()?.toDTO() else null
        } catch (e: Exception) {
            null
        }
    }

    // =========================
    // DELETE
    // =========================
    suspend fun deleteBand(id: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteBand(id)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}