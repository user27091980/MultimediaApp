package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.BandDTO

/**
 * Interfaz que define las operaciones de un repositorio de bandas.
 */
interface IBandsRepo {

    // CREATE: Añade una nueva banda y devuelve la creada
    fun createBand(band: BandDTO): BandDTO

    // READ ALL: Devuelve todas las bandas
    fun getAllBands(): List<BandDTO>

    // READ por ID: Devuelve la banda con el ID especificado o null si no existe
    fun getBandById(id: String): BandDTO?

    // UPDATE: Actualiza la banda, devuelve true si se actualizó correctamente
    fun updateBand(band: BandDTO): Boolean

    // DELETE: Elimina la banda por ID, devuelve true si se eliminó correctamente
    fun deleteBand(id: String): Boolean
}