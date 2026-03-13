package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.BandEntity
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.network.BandApiService

// Interfaz del repositorio para BandEntity / BandDTO
interface IBandRepo {

    suspend fun getBands(): List<BandDTO>

    suspend fun getBandById(id: String): BandDTO?

    suspend fun createBand(band: BandDTO): BandDTO?

    suspend fun updateBand(id: String, band: BandDTO): BandDTO?

    suspend fun deleteBand(id: String): Boolean
}

// Implementación del repositorio usando Retrofit
class BandsRepo(private val bandApi: BandApiService) : IBandRepo {
    // Recupera todas las bandas y las convierte de Entity a DTO
    override suspend fun getBands(): List<BandDTO> {
        val response = bandApi.getBands()
        return if (response.isSuccessful) {
            // Si la respuesta es exitosa, mapea cada elemento de la lista a DTO
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else emptyList()
    }
    // Busca una banda por ID y la convierte a DTO si existe
    override suspend fun getBandById(id: String): BandDTO? {
        val response = bandApi.getBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Crea una nueva banda. Nota: Aquí se realiza el mapeo manual de DTO
    override suspend fun createBand(band: BandDTO): BandDTO? {
        val entity = BandEntity(
            id = band.id,
            name = band.name,
            description = band.description,
            banner = band.banner,
            albumImages = band.albumImages,
            style = band.style,
            recordLabel = band.recordLabel,
            components = band.components,
            discography = band.discography,
            albumLinks = band.albumLinks,
            headerLink = band.headerLink,

        )
        val response = bandApi.createBand(entity)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Actualiza los datos de una banda existente mediante su ID
    override suspend fun updateBand(id: String, band: BandDTO): BandDTO? {
        val entity = BandEntity(
            id = band.id,
            name = band.name,
            description = band.description,
            banner = band.banner,
            albumImages = band.albumImages,
            style = band.style,
            recordLabel = band.recordLabel,
            components = band.components,
            discography = band.discography,
            albumLinks = band.albumLinks,
            headerLink = band.headerLink
        )
        val response = bandApi.updateBand(id, entity)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Elimina una banda y devuelve true si la operación fue exitosa en el servidor
    override suspend fun deleteBand(id: String): Boolean {
        val response = bandApi.deleteBand(id)
        return response.isSuccessful
    }
}