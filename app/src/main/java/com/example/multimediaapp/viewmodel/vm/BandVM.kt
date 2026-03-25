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
 * ViewModel encargado de gestionar la lógica de negocio relacionada con las bandas.
 *
 * Actúa como intermediario entre la UI y la capa de datos.
 *
 * Responsabilidades:
 * - Obtener datos desde el repositorio
 * - Exponer el estado mediante [StateFlow]
 * - Manejar operaciones como carga y creación de bandas
 *
 * @property repository Repositorio que gestiona las operaciones de red.
 */
class BandVM : ViewModel() {

    /**
     * Instancia del repositorio de bandas.
     */
    private val repository = BandsRepo(RetrofitModule.bandApi)

    /**
     * Estado interno de la lista de bandas.
     */
    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())

    /**
     * Estado público de la lista de bandas observable desde la UI.
     */
    val bandsState = _bandsState.asStateFlow()

    /**
     * Estado de error observable desde la UI.
     */
    private val _error = MutableStateFlow<String?>(null)

    /**
     * Estado público de error observable desde la UI.
     */
    val error = _error.asStateFlow()

    /**
     * Carga todas las bandas desde el repositorio.
     *
     * Actualiza el estado [bandsState] en caso de éxito.
     * En caso de error, actualiza [error].
     */
    fun loadAllBands() {
        viewModelScope.launch {
            try {
                val bands = repository.getBands()
                _bandsState.value = bands
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    /**
     * Crea una nueva banda.
     *
     * Después de crearla, recarga la lista de bandas.
     *
     * @param band Objeto [BandDTO] con los datos de la nueva banda.
     */
    fun createBand(band: BandDTO) {
        viewModelScope.launch {
            try {
                repository.createBand(band)

                // Recargar datos tras crear la banda
                loadAllBands()

            } catch (e: Exception) {
                _error.value = "Error creando banda"
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