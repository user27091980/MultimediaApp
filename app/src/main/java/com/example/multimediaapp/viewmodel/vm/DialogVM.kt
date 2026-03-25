package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel que gestiona el estado de un diálogo de confirmación.
 *
 * Se encarga de controlar la visibilidad del diálogo y las acciones
 * asociadas a su confirmación o cancelación.
 *
 * El estado se expone mediante [StateFlow] para que la UI pueda observar
 * los cambios de forma reactiva.
 */
class DialogVM : ViewModel() {

    /**
     * Estado interno que representa la visibilidad del diálogo.
     *
     * - true: el diálogo es visible
     * - false: el diálogo está oculto
     */
    private val _uiState = MutableStateFlow(false)

    /**
     * Estado observable del diálogo expuesto a la UI.
     */
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    /**
     * Muestra el diálogo.
     *
     * Cambia el estado a `true`, lo que provoca que la UI renderice el diálogo.
     */
    fun showDialog() {
        _uiState.value = true
    }

    /**
     * Oculta el diálogo.
     *
     * Cambia el estado a `false`, ocultando el diálogo en la UI.
     */
    fun hideDialog() {
        _uiState.value = false
    }

    /**
     * Ejecuta la acción de confirmación del diálogo.
     *
     * Actualmente solo oculta el diálogo, pero puede ampliarse
     * para ejecutar lógica adicional como confirmar acciones del usuario.
     */
    fun confirmAction() {
        _uiState.value = false
    }
}
/**
 * DialogVM (ViewModel del diálogo):
 *
 * Este ViewModel gestiona el estado de un diálogo de confirmación
 * dentro de la arquitectura MVVM.
 *
 * OBJETIVO:
 *
 * - Controlar si el diálogo se muestra o no.
 * - Separar la lógica del estado de la UI.
 * - Permitir que la UI reaccione de forma automática a los cambios.
 *
 * ARQUITECTURA:
 *
 * UI (Compose)
 *     ↓
 * DialogVM
 *
 * FLUJO DE FUNCIONAMIENTO:
 *
 * 1. La UI observa el estado:
 *      val state by viewModel.uiState.collectAsState()
 *
 * 2. Cuando cambia el estado:
 *      - true → se muestra el AlertDialog
 *      - false → se oculta el AlertDialog
 *
 * 3. La UI llama a funciones del ViewModel:
 *      - showDialog()
 *      - hideDialog()
 *      - confirmAction()
 *
 * VARIABLES DE ESTADO:
 *
 * _uiState:
 * - MutableStateFlow interno.
 * - Representa la visibilidad del diálogo.
 * - true → visible
 * - false → oculto
 *
 * uiState:
 * - Versión pública (solo lectura).
 * - Es la que consume la UI.
 *
 * MÉTODOS:
 *
 * showDialog():
 * - Cambia el estado a true.
 * - Hace visible el diálogo.
 *
 * hideDialog():
 * - Cambia el estado a false.
 * - Oculta el diálogo.
 *
 * confirmAction():
 * - Ejecuta la acción de confirmación.
 * - Actualmente solo oculta el diálogo.
 * - Puede ampliarse para realizar lógica adicional
 *   (guardar datos, navegar, ejecutar acciones, etc.).
 *
 * BENEFICIOS:
 *
 * - UI reactiva y desacoplada de la lógica.
 * - Código más limpio y mantenible.
 * - Facilita pruebas y escalabilidad.
 *
 * NOTA:
 *
 * - Se utiliza StateFlow para emitir cambios de estado.
 * - Compose se encarga de recomponer automáticamente la UI.
 */