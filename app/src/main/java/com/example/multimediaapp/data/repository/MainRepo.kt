package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.MainApiService
/**
 * Interfaz de repositorio para la entidad Principal (Main).
 * Define las operaciones CRUD y de obtención de recursos multimedia.
 */

interface IMainRepo {
    suspend fun getBands(): List<MainDTO>
    suspend fun getBandById(id: String): MainDTO?
    suspend fun createBand(band: MainDTO): MainDTO?
    suspend fun updateBand(id: String, band: MainDTO): MainDTO?
    suspend fun deleteBand(id: String): Boolean
    // Método específico para recuperar imágenes/galería por ID
    suspend fun getImages(id: String): MainDTO

}
/**
 * Implementación de IMainRepo que utiliza MainApiService (Retrofit)
 * para la comunicación con el servidor.
 */
class MainRepo(private val mainApi: MainApiService) : IMainRepo {
    // Obtiene todas las bandas y las transforma a DTO (Data Transfer Object)
    override suspend fun getBands(): List<MainDTO> {
        val response = mainApi.getMainBands() // Tu endpoint de la pantalla principal
        return if (response.isSuccessful) {
            // Mapea la lista de entidades recibidas a una lista de objetos DTO
            response.body()?.map { it.toDTO() } ?: emptyList()
        } else {
            emptyList()
        }
    }
    // Obtiene una banda específica buscando por su identificador único
    override suspend fun getBandById(id: String): MainDTO? {
        val response = mainApi.getMainBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Crea una nueva banda convirtiendo primero el DTO a Entity para la API
    override suspend fun createBand(band: MainDTO): MainDTO? {
        val response = mainApi.createMainBand(band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Actualiza una banda existente enviando el ID y el objeto convertido a Entity
    override suspend fun updateBand(id: String, band: MainDTO): MainDTO? {
        val response = mainApi.updateMainBand(id, band.toEntity())
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }
    // Elimina un registro y retorna un booleano indicando el éxito de la operación
    override suspend fun deleteBand(id: String): Boolean {
        val response = mainApi.deleteMainBand(id)
        return response.isSuccessful
    }
    // Obtiene información de imágenes específica.
    // Nota: Lanza excepciones manualmente si la respuesta falla o el cuerpo es nulo.
    override suspend fun getImages(id: String): MainDTO {
        val response = mainApi.getMainImages(id)
        if (response.isSuccessful) {
            return response.body()?.toDTO() ?: throw Exception("Cuerpo vacío")
        }
        throw Exception("Error al obtener imágenes: ${response.code()}")
    }

}