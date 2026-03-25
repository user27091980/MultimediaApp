package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.network.BandApiService
import java.io.IOException

// Interfaz del repositorio para BandEntity / BandDTO(permite testear y cambiar la implementación)
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
        } else throw IOException("Error al obtener bandas: ${response.code()}")
    }
    // Busca una banda por ID y la convierte a DTO si existe
    override suspend fun getBandById(id: String): BandDTO? {
        val response = bandApi.getBandById(id)
        return if (response.isSuccessful) response.body()?.toDTO() else null
    }


    // Crea una nueva banda. Nota: Aquí se realiza el mapeo manual de DTO
    override suspend fun createBand(band: BandDTO): BandDTO? {
        val entity = band.toEntity()
        val response = bandApi.createBand(entity)

        return if (response.isSuccessful) {
            response.body()?.toDTO()
        } else {
            throw IOException("Error al crear banda: ${response.code()}")
        }
    }
    // Actualiza los datos de una banda existente mediante su ID
    override suspend fun updateBand(id: String, band: BandDTO): BandDTO? {
        val entity = band.toEntity()
        val response = bandApi.updateBand(id, entity)

        return if (response.isSuccessful) {
            response.body()?.toDTO()
        } else {
            throw IOException("Error al actualizar: ${response.code()}")
        }
    }
    // Elimina una banda y devuelve true si la operación fue exitosa en el servidor
    override suspend fun deleteBand(id: String): Boolean {
        val response = bandApi.deleteBand(id)
        return response.isSuccessful
    }

}
/*
 * Este archivo define el repositorio de bandas, encargado de gestionar
 * la comunicación entre la aplicación y la API.
 *
 * La interfaz IBandRepo define las operaciones disponibles:
 * - Obtener todas las bandas
 * - Obtener una banda por ID
 * - Crear una banda
 * - Actualizar una banda
 * - Eliminar una banda
 *
 * Esta interfaz permite desacoplar la lógica de la implementación concreta,
 * facilitando pruebas y cambios en el futuro.
 *
 * La clase BandsRepo implementa esta interfaz utilizando un servicio de red (BandApiService),
 * normalmente basado en Retrofit.
 *
 * Funcionamiento general:
 *
 * 1. getBands():
 *    - Llama a la API para obtener todas las bandas.
 *    - Si la respuesta es exitosa, convierte cada BandEntity a BandDTO.
 *    - Si falla, lanza una excepción.
 *
 * 2. getBandById(id):
 *    - Obtiene una banda específica por su ID.
 *    - Convierte el resultado a DTO si la respuesta es correcta.
 *
 * 3. createBand(band):
 *    - Convierte el DTO a Entity antes de enviarlo al servidor.
 *    - Llama al endpoint de creación.
 *    - Convierte la respuesta de vuelta a DTO.
 *
 * 4. updateBand(id, band):
 *    - Convierte el DTO a Entity.
 *    - Envía la actualización al servidor.
 *    - Devuelve el resultado convertido a DTO.
 *
 * 5. deleteBand(id):
 *    - Llama al endpoint de eliminación.
 *    - Devuelve true si la operación fue exitosa.
 *
 * Este repositorio actúa como intermediario entre:
 * - La capa de red (API)
 * - La capa de datos (Entity)
 * - La capa de presentación (DTO)
 *
 * Además, centraliza el manejo de errores mediante IOException,
 * lo que permite gestionar fallos de red o del servidor de forma controlada.
 */