package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.viewmodel.uistate.BandListUiState
import com.example.multimediaapp.viewmodel.uistate.BandUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel encargado de gestionar la lista de bandas.
 *
 * Funciona dentro de la arquitectura MVVM:
 * - No contiene lógica de UI.
 * - Se comunica con el repositorio para obtener y manipular datos.
 * - Expone un estado observable (StateFlow) que la UI observa.
 */
class BandVM : ViewModel() {
    /**
     * Estado interno mutable.
     * Solo se puede modificar dentro del ViewModel.
     */
    private val _uiState = MutableStateFlow(BandListUiState())
    /**
     * Estado público inmutable.
     * La UI observa este flujo para reaccionar a cambios.
     */
    val uiState: StateFlow<BandListUiState> = _uiState.asStateFlow()
    /**
     * Repositorio que actúa como fuente de datos de las bandas.
     * Actualmente simula almacenamiento en memoria.
     */
    val repo: BandsRepo = BandsRepo()
    // -------------------------------------------------
    // MÉTODOS DE MANEJO DE DATOS
    // -------------------------------------------------

    /**
     * Reinicia el estado de la UI a vacío.
     * Útil para limpiar la lista o reiniciar la pantalla.
     */
    fun loadDatos() {
        _uiState.value = BandListUiState()
    }
    /**
     * Elimina una banda por su ID.
     *
     * 1️⃣ Llama al repositorio.
     * 2️⃣ Si tiene éxito → recarga la lista con `loadData()`.
     * 3️⃣ Si falla → se podría manejar error (comentado actualmente).
     *
     * @param id ID de la banda a eliminar
     */
    fun del(id: String) {
        repo.delete(id, onSuccess = {
            // Recargamos la lista tras eliminar la banda
            loadData()
        }) {
            /*Error al eliminar
            _uiState.value = _uiState.value.copy(
                error = "Error al eliminar la banda con ID $id")

             */
        }
    }
    /**
     * Carga todas las bandas desde el repositorio.
     *
     * Convierte cada objeto de datos (`BandDTO`) en un objeto de UI (`BandUiState`).
     * Permite que la UI observe los cambios y los muestre automáticamente.
     */
    fun loadData() {
        repo.readAll({
            _uiState.value = BandListUiState(it.map {
                BandUiState(
                    it.id,
                    it.name,
                    it.textInfo,
                    it.headerImage,
                    it.albumImages,
                    it.style,
                    it.recordLabel,
                    it.components,
                    it.discography,
                    it.imageBand
                )
            }.toList())
        }) {
            /*READLL ON ERROR
            _uiState.value = _uiState.value.copy(
                error = "Error al cargar bandas"
            )*/
        }
    }
}
