package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersRepo
import com.example.multimediaapp.viewmodel.uistate.UserListUiState
import com.example.multimediaapp.viewmodel.uistate.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel encargado de gestionar la lista de usuarios.
 *
 * Forma parte de la arquitectura MVVM:
 * - No contiene lógica de UI
 * - Se comunica con el repositorio
 * - Expone un estado observable (StateFlow)
 */
class UserListVM : ViewModel(){
    /**
     * Estado interno mutable.
     * Solo puede ser modificado dentro del ViewModel.
     */
    private val _uiState = MutableStateFlow(UserListUiState())
    /**
     * Estado expuesto como solo lectura.
     * La UI lo observa para reaccionar a cambios.
     */
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()
    /**
     * Repositorio que actúa como fuente de datos.
     * En una app real podría conectarse a:
     * - API REST
     * - Base de datos Room
     * - Firebase
     */
    private val repo: UsersRepo = UsersRepo()
    /**
     * Reinicia el estado.
     * Actualmente solo crea un estado vacío.
     */
    fun reset() {
        _uiState.value = UserListUiState()
    }
    /**
     * Elimina un usuario por ID.
     *
     * 1️⃣ Llama al repositorio
     * 2️⃣ Si tiene éxito → recarga la lista
     * 3️⃣ Si falla → se podría manejar error
     */
    fun deleteUser(id: String) {
        repo.delete(id, onSuccess = {
            // Si se elimina correctamente, recargamos datos
            reset()
        }) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Error al eliminar usuarios")
        }
    }
    /**
     * Carga todos los usuarios desde el repositorio.
     *
     * Convierte el modelo de datos (Repo) al modelo de UI.
     */
    fun loadData() {
        // Si la lectura fue exitosa
        repo.readAll({
            // Convertimos cada objeto del repositorio
            // en un objeto de UI

            _uiState.value = UserListUiState(it.map {

                UserUIState(
                    it.id,
                    it.user,
                    it.pass
                )
            }.toList())
        }) {
            //READLL ON ERROR
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Error al cargar usuarios")
        }
    }
}