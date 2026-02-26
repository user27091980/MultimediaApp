package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.RegisterListUiState
import com.example.multimediaapp.viewmodel.uistate.RegisterUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar los registros de usuarios.
 *
 * Funciona dentro de la arquitectura MVVM:
 * - No tiene lógica de UI.
 * - Expone un estado observable (_uiState) para la UI.
 * - Permite agregar, eliminar y limpiar registros de forma reactiva.
 */
class RegisterVM : ViewModel() {

    /**
     * Estado interno mutable.
     * Solo se modifica dentro del ViewModel.
     */
    private val _uiState = MutableStateFlow(RegisterListUiState())

    // Estado público inmutable
    val uiState: StateFlow<RegisterListUiState> = _uiState.asStateFlow()

    /**
     * Agrega un nuevo registro de usuario.
     *
     * Genera automáticamente un ID incremental basado en los registros existentes.
     * @param user nombre de usuario
     * @param pass contraseña
     */
    // Agregar usuario eliminamos id ya que estamos creando newId
    fun addRegister(user: String, pass: String) {
        val currentList = _uiState.value.regUser.toMutableList()
// Generamos un nuevo ID incremental como String
        val newId = if (currentList.isEmpty()) "1"
        else (currentList.maxOf { it.id.toInt() } + 1).toString()
// Creamos el nuevo objeto de registro

        val newRegister = RegisterUIState(
            id = newId,
            user = user,
            pass = pass
        )
// Agregamos el nuevo registro a la lista
        currentList.add(newRegister)
// Actualizamos el estado completo con la lista nueva
        _uiState.value = RegisterListUiState(regUser = currentList)
    }

    /**
     * Elimina un registro por su ID.
     * @param id ID del usuario a eliminar
     */
    fun removeRegister(id: String) {
        // Filtramos todos los registros que no coincidan con el ID
        val updatedList = _uiState.value.regUser.filter { it.id != id }
        // Actualizamos el estado con la lista filtrada
        _uiState.value = RegisterListUiState(regUser = updatedList)
    }

    /**
     * Limpia todos los registros.
     * Resetea el estado a vacío.
     */
    fun clearRegisters() {
        _uiState.value = RegisterListUiState()
    }
}