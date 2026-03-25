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
/**
 * BandVM (ViewModel de Bandas):
 *
 * Este ViewModel forma parte de la arquitectura MVVM y actúa como intermediario
 * entre la capa de datos (repositorio) y la capa de presentación (UI).
 *
 * RESPONSABILIDADES PRINCIPALES:
 *
 * - Obtener datos de las bandas desde el repositorio (API o fuente de datos).
 * - Exponer el estado de la UI mediante StateFlow.
 * - Manejar operaciones de negocio como:
 *      • Cargar bandas
 *      • Crear nuevas bandas
 * - Gestionar errores y propagarlos a la UI.
 *
 * ARQUITECTURA:
 *
 * UI (Compose)
 *     ↓
 * ViewModel (BandVM)
 *     ↓
 * Repository (BandsRepo)
 *     ↓
 * API (RetrofitModule.bandApi)
 *
 * FLUJO DE DATOS:
 *
 * 1. La UI llama a un método del ViewModel (ej: loadAllBands()).
 * 2. El ViewModel ejecuta la lógica dentro de viewModelScope.
 * 3. El repositorio obtiene los datos (API).
 * 4. El ViewModel actualiza el StateFlow (_bandsState).
 * 5. La UI observa el StateFlow y se recompondrá automáticamente.
 *
 * VARIABLES DE ESTADO:
 *
 * _bandsState:
 * - Estado interno (MutableStateFlow).
 * - Contiene la lista de bandas.
 *
 * bandsState:
 * - Estado público (solo lectura).
 * - Es el que observa la UI.
 *
 * _error / error:
 * - Maneja posibles errores en las operaciones.
 *
 * MÉTODOS:
 *
 * loadAllBands():
 * - Obtiene todas las bandas desde el repositorio.
 * - Maneja errores con try-catch.
 * - Actualiza el estado observable.
 *
 * createBand(band):
 * - Envía una nueva banda al backend.
 * - Recarga la lista después de crearla.
 *
 * viewModelScope:
 * - Scope de corrutinas ligado al ciclo de vida del ViewModel.
 * - Se cancela automáticamente cuando el ViewModel se destruye.
 *
 * BENEFICIOS:
 *
 * - Separación de responsabilidades.
 * - Código más limpio y mantenible.
 * - UI reactiva basada en estados.
 * - Seguridad frente a cambios de configuración (rotación).
 *
 * NOTA:
 *
 * - El repositorio utiliza Retrofit para comunicarse con la API.
 * - Este ViewModel evita que la UI tenga lógica de negocio o acceso directo a datos.
 */