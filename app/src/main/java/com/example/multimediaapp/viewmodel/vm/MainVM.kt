package com.example.multimediaapp.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla principal.
 *
 * Gestiona la obtención y el estado de la lista de bandas mostradas en la UI.
 *
 * Expone el estado mediante [StateFlow] para permitir una UI reactiva.
 *
 * @property repository Repositorio encargado de obtener los datos.
 */
class MainVM : ViewModel() {

    /**
     * Repositorio de datos para obtener la información de las bandas.
     */
    private val repository = MainRepo(RetrofitModule.mainApi)

    /**
     * Estado interno de la UI.
     */
    private val _uiState = MutableStateFlow(MainUiState())

    /**
     * Estado observable de la UI.
     */
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * Inicializa la carga de datos al crear el ViewModel.
     */
    init {
        loadData()
    }

    /**
     * Carga la lista de bandas desde el repositorio.
     *
     * Actualiza el estado de la UI:
     * - [MainUiState.isLoading] durante la carga
     * - [MainUiState.mainBands] con los datos obtenidos
     * - [MainUiState.error] en caso de fallo
     */
    fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val bands = repository.getBands()

                _uiState.value = _uiState.value.copy(
                    mainBands = bands,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
/**
 * MainVM (ViewModel de la pantalla principal):
 *
 * Este ViewModel gestiona la lógica de la pantalla principal (MainScreen),
 * encargándose de obtener y mantener la lista de bandas que se muestran en la UI.
 *
 * FORMA PARTE DE MVVM:
 *
 * - Actúa como intermediario entre la UI y la capa de datos.
 * - Obtiene datos desde el repositorio.
 * - Expone el estado mediante StateFlow.
 * - Permite que la UI sea reactiva.
 *
 * ARQUITECTURA:
 *
 * UI (Compose)
 *     ↓
 * MainVM
 *     ↓
 * MainRepo
 *     ↓
 * Retrofit API
 *
 * VARIABLES:
 *
 * repository:
 * - Instancia de MainRepo.
 * - Se encarga de obtener los datos desde la API.
 *
 * _uiState:
 * - Estado interno mutable.
 * - Tipo: MutableStateFlow<MainUiState>.
 * - Contiene:
 *      • lista de bandas
 *      • estado de carga
 *      • errores
 *
 * uiState:
 * - Estado público (solo lectura).
 * - Observado por la UI.
 *
 * FLUJO DE FUNCIONAMIENTO:
 *
 * 1. El ViewModel se crea.
 *
 * 2. En el init:
 *      → se llama automáticamente a loadData().
 *
 * 3. loadData():
 *      - Marca la UI como cargando (isLoading = true).
 *      - Llama al repositorio para obtener datos.
 *      - Actualiza el estado con la lista obtenida.
 *      - Maneja errores si ocurren.
 *
 * 4. La UI observa uiState:
 *      → collectAsState()
 *      → se recompone automáticamente al cambiar el estado.
 *
 * MÉTODO PRINCIPAL:
 *
 * loadData():
 * - Ejecuta una corrutina en viewModelScope.
 * - Llama a repository.getBands().
 * - Actualiza el estado:
 *      • isLoading = true durante la carga
 *      • mainBands = lista obtenida
 *      • isLoading = false al finalizar
 * - Captura errores con try-catch.
 *
 * viewModelScope:
 * - Scope de corrutinas asociado al ciclo de vida del ViewModel.
 * - Se cancela automáticamente al destruirse.
 *
 * BENEFICIOS:
 *
 * - UI completamente desacoplada de la lógica de datos.
 * - Estado centralizado y observable.
 * - Manejo limpio de errores.
 * - Código más mantenible y escalable.
 *
 * NOTA:
 *
 * - StateFlow garantiza que la UI siempre tenga el estado más reciente.
 * - El uso de copy() evita mutaciones directas (inmutabilidad).
 */