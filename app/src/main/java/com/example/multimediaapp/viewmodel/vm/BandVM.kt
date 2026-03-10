package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.model.BandDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel que gestiona la lista de bandas usando BandsRepo.
 * Expone un StateFlow que la UI puede observar.
 */
class BandVM : ViewModel() {

    private val repo = BandsRepo()

    // Estado interno mutable
    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())

    // Estado público inmutable
    val bandsState: StateFlow<List<BandDTO>> = _bandsState.asStateFlow()

    // ======================================
    // CRUD Methods
    // ======================================

    /** Carga todas las bandas del repositorio */
    fun loadAllBands() {
        val allBands = repo.getAllBands()
        _bandsState.value = allBands
    }

    /** Agrega una nueva banda */
    fun createBand(band: BandDTO) {
        repo.createBand(band)
        loadAllBands() // Actualiza la lista
    }

    /** Actualiza una banda existente */
    fun updateBand(band: BandDTO) {
        repo.updateBand(band)
        loadAllBands()
    }

    /** Elimina una banda por ID */
    fun deleteBand(id: String) {
        repo.deleteBand(id)
        loadAllBands()
    }

    /** Obtiene una banda por ID (sin modificar el estado) */
    fun getBandById(id: String): BandDTO? {
        return repo.getBandById(id)
    }
}
