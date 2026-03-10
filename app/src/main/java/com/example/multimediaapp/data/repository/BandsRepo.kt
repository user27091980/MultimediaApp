package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.BandDTO

/**
 * Implementación en memoria de IBandsRepo.
 * Simula un repositorio con CRUD de bandas.
 */
class BandsRepo : IBandsRepo {

    // Lista mutable que almacena todas las bandas
    private val bands = mutableListOf<BandDTO>()

    // Último ID usado, se incrementa al crear nuevas bandas
    private var currId = 0


    // CRUD

    // CREATE
    override fun createBand(band: BandDTO): BandDTO {
        currId++
        val newBand = band.copy(id = currId.toString())
        bands.add(newBand)
        return newBand
    }

    // READ ALL
    override fun getAllBands(): List<BandDTO> {
        return bands.toList() // Copia inmutable
    }

    // READ por ID
    override fun getBandById(id: String): BandDTO? {
        return bands.find { it.id == id }
    }

    // UPDATE
    override fun updateBand(band: BandDTO): Boolean {
        val index = bands.indexOfFirst { it.id == band.id }
        return if (index != -1) {
            bands[index] = band
            true
        } else {
            false
        }
    }

    // DELETE
    override fun deleteBand(id: String): Boolean {
        return bands.removeIf { it.id == id }
    }
}