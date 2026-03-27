package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.retrofit.RetrofitModule.bandApi

/**
 * Interfaz de repositorio para la entidad principal (Main).
 *
 * Define las operaciones CRUD y métodos de obtención de recursos multimedia,
 * permitiendo desacoplar la capa de datos de la capa de presentación
 * y facilitando pruebas unitarias.
 */
interface IMainRepo {

    /** Obtiene la lista completa de bandas. */
    suspend fun getBands(): List<MainDTO>

    /** Obtiene una banda específica por su ID. */
    suspend fun getBandById(id: String): MainDTO?

    /** Crea una nueva banda en el servidor y devuelve el objeto creado. */
    suspend fun createBand(band: MainDTO): MainDTO?

    /** Actualiza una banda existente mediante su ID. */
    suspend fun updateBand(id: String, band: MainDTO): MainDTO?

    /** Elimina una banda por su ID. Retorna true si la operación fue exitosa. */
    suspend fun deleteBand(id: String): Boolean

    /** Obtiene la lista de URLs de imágenes asociadas a una banda/álbum. */
    suspend fun getImages(id: String): List<String>
}

/**
 * Implementación de [IMainRepo] usando [MainApiService] (Retrofit).
 *
 * Cada método se encarga de:
 * 1. Llamar al endpoint correspondiente del servidor.
 * 2. Convertir las entidades recibidas a DTOs para la capa de presentación.
 * 3. Manejar respuestas vacías o fallidas de manera segura.
 *
 * @property mainApi Servicio Retrofit que proporciona los endpoints.
 */
class MainRepo(private val mainApi: MainApiService) : IMainRepo {

    /** Obtiene todas las bandas y las transforma a DTOs. */
    override suspend fun getBands(): List<MainDTO> {
        val response = mainApi.getMainBands()
        return if (response.isSuccessful) {
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else {
            emptyList() // En caso de error, retorna lista vacía
        }
    }

    /** Obtiene una banda específica por su ID y la transforma a DTO. */
    override suspend fun getBandById(id: String): MainDTO? {
        val response = mainApi.getMainBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    /** Crea una nueva banda convirtiendo el DTO a Entity antes de enviarlo. */
    override suspend fun createBand(band: MainDTO): MainDTO? {
        val response = mainApi.createMainBand(band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    /** Actualiza una banda existente enviando el ID y la banda convertida a Entity. */
    override suspend fun updateBand(id: String, band: MainDTO): MainDTO? {
        val response = mainApi.updateMainBand(id, band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    /** Elimina una banda y devuelve true si la operación fue exitosa en el servidor. */
    override suspend fun deleteBand(id: String): Boolean {
        val response = mainApi.deleteMainBand(id)
        return response.isSuccessful
    }

    /** Obtiene las imágenes asociadas a una banda/álbum. */
    override suspend fun getImages(id: String): List<String> {
        val response = bandApi.getAlbumImages(id)
        return if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }
}