package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.network.BandApiService
import java.io.IOException

/**
 * Interfaz del repositorio de bandas.
 *
 * Define las operaciones disponibles sobre bandas: lectura, creación,
 * actualización y eliminación. Usar una interfaz permite cambiar la
 * implementación (por ejemplo, pruebas unitarias con mocks) sin afectar
 * la capa superior (ViewModel o UI).
 */
interface IBandRepo {

    /** Obtiene la lista completa de bandas */
    suspend fun getBands(): List<BandDTO>

    /** Obtiene una banda específica por su ID */
    suspend fun getBandById(id: String): BandDTO?

    /** Crea una nueva banda y devuelve la banda creada (con ID generado) */
    suspend fun createBand(band: BandDTO): BandDTO?

    /** Actualiza una banda existente identificada por su ID */
    suspend fun updateBand(id: String, band: BandDTO): BandDTO?

    /** Elimina una banda por su ID y devuelve true si la operación fue exitosa */
    suspend fun deleteBand(id: String): Boolean
}

/**
 * Implementación del repositorio usando Retrofit.
 *
 * Este repositorio realiza las llamadas HTTP a la API de bandas
 * y mapea los datos de Entity a DTO para exponerlos a la capa de
 * presentación.
 *
 * @property bandApi Servicio Retrofit para acceder a la API de bandas.
 */
class BandsRepo(private val bandApi: BandApiService) : IBandRepo {

    /**
     * Recupera todas las bandas de la API.
     * Convierte cada elemento de la lista de Entity a DTO.
     *
     * @throws IOException si la respuesta no es exitosa.
     */
    override suspend fun getBands(): List<BandDTO> {
        val response = bandApi.getBands()
        return if (response.isSuccessful) {
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else throw IOException("Error al obtener bandas: ${response.code()}")
    }

    /**
     * Busca una banda por su ID.
     *
     * @return BandDTO si existe, null si no se encuentra o hay error.
     */
    override suspend fun getBandById(id: String): BandDTO? {
        val response = bandApi.getBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }

    /**
     * Crea una nueva banda en la API.
     *
     * Convierte el DTO a Entity para enviarlo a la API y luego
     * mapea la respuesta de nuevo a DTO.
     *
     * @throws IOException si la creación falla.
     */
    override suspend fun createBand(band: BandDTO): BandDTO? {
        val entity = band.toEntity()
        val response = bandApi.createBand(entity)

        return if (response.isSuccessful) response.body()?.toDTO()
        else throw IOException("Error al crear banda: ${response.code()}")
    }

    /**
     * Actualiza los datos de una banda existente.
     *
     * @param id ID de la banda a actualizar
     * @param band Datos actualizados
     * @throws IOException si la actualización falla
     */
    override suspend fun updateBand(id: String, band: BandDTO): BandDTO? {
        val entity = band.toEntity()
        val response = bandApi.updateBand(id, entity)

        return if (response.isSuccessful) response.body()?.toDTO()
        else throw IOException("Error al actualizar: ${response.code()}")
    }

    /**
     * Elimina una banda de la API.
     *
     * @return true si la operación fue exitosa, false en caso contrario
     */
    override suspend fun deleteBand(id: String): Boolean {
        val response = bandApi.deleteBand(id)
        return response.isSuccessful
    }
}