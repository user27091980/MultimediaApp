package com.example.multimediaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar BandDTO usando BandsRepo.
 *
 * - Se encarga de la lógica de negocio.
 * - Expone los datos a la UI mediante StateFlow (reactivo).
 * - Maneja operaciones CRUD de bandas.
 */
class BandVM : ViewModel() {

    // Repositorio que gestiona acceso a datos (API REST con Retrofit)
    private val repository = BandsRepo(RetrofitModule.bandApi)
    private val _isCreated = MutableStateFlow(false)
    val isCreated: StateFlow<Boolean> = _isCreated.asStateFlow()

    //Estado y lista de bandas observable desde la UI
    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())
    val bandsState: StateFlow<List<BandDTO>> = _bandsState.asStateFlow()

    //banda seleccionada
    private val _selectedBand = MutableStateFlow<BandDTO?>(null)
    val selectedBand: StateFlow<BandDTO?> = _selectedBand.asStateFlow()
    // Manejo de errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Inicialización
    init {
        // Al crear el ViewModel, carga todas las bandas automáticamente
        loadAllBands()
    }

    //Funciones CRUD
    /**
     * Obtiene todas las bandas desde el repositorio
     * y actualiza el estado.
     */
    fun loadAllBands() {
        viewModelScope.launch {
            try {
                _error.value = null
                val bands = repository.getBands()
                _bandsState.value = bands
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error desconocido"
            }
        }
    }
    

    /**
     * Crea una nueva banda en el backend
     * y la añade al estado local si tiene éxito.
     */
    fun createBand(band: BandDTO) {
        viewModelScope.launch {
            try {
                _error.value = null
                _isCreated.value = false

                val created = repository.createBand(band)

                created?.let {
                    _bandsState.value = _bandsState.value + it
                    _isCreated.value = true // clave para UI
                }

            } catch (e: Exception) {
                _error.value = "Error al crear: ${e.localizedMessage ?: "desconocido"}"
            }
        }
    }
    /**
     * Actualiza una banda existente.
     * Reemplaza el elemento en la lista local.
     */
    fun updateBand(id: String, band: BandDTO) {
        viewModelScope.launch {
            try {
                val updated = repository.updateBand(id, band)
                updated?.let { bandUpdated ->
                    _bandsState.value =
                        _bandsState.value.map { if (it.id == id) bandUpdated else it }
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error al actualizar"
            }
        }
    }
    /**
     * Elimina una banda por ID.
     * Si el backend confirma, se elimina del estado local.
     */
    fun deleteBand(id: String) {
        viewModelScope.launch {
            try {
                val deleted = repository.deleteBand(id)
                if (deleted) {
                    _bandsState.value = _bandsState.value.filter { it.id != id }
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error al eliminar"
            }
        }
    }
}
/*
Un ViewModel es una pieza clave de la arquitectura Android (especialmente en MVVM)
que sirve como puente entre la UI y los datos.
Un ViewModel es una clase que:
Guarda y gestiona datos de la UI
Sobrevive a cambios como rotación de pantalla
Separa la lógica de la interfaz
Es el “cerebro” de tu pantalla.
 */