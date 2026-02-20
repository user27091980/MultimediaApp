package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de manejar la lógica de usuarios.
 *
 * Forma parte de la arquitectura MVVM.
 * - No contiene lógica de UI
 * - Gestiona el estado
 * - Se comunica con el repositorio
 */
class UserInfoVM : ViewModel() {
    /**
     * Estado interno mutable.
     * Solo el ViewModel puede modificarlo.
     */
    private val _uiState = MutableStateFlow(UserInfoListUiState())
    /**
     * Estado expuesto como solo lectura.
     * La UI solo puede observarlo.
     */
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()
    /**
     * Repositorio que simula la fuente de datos.
     */
    val repo: UsersInfoRepo = UsersInfoRepo()
    /**
     * Añade un nuevo usuario al estado actual.
     */
    fun addUser(user: UserInfoUiState) {
        // Copiamos la lista actual a mutable
        val currentList = _uiState.value.userInfo.toMutableList()
        // Añadimos el nuevo usuario
        currentList.add(user)
        // Actualizamos el estado con la nueva lista
        _uiState.value = UserInfoListUiState(currentList)
    }
    /**
     * Actualiza un usuario existente buscando por ID.
     */
    fun updateUser(updatedUser: UserInfoUiState) {
        // Recorremos la lista y reemplazamos el usuario con el mismo ID
        val updatedList = _uiState.value.userInfo.map {
            if (it.id == updatedUser.id) updatedUser else it
        }
        // Actualizamos el estado
        _uiState.value = UserInfoListUiState(updatedList)
    }
    /**
     * Elimina un usuario por ID.
     */
    fun deleteUser(userId: String) {
        // Filtramos todos los usuarios excepto el que coincide con el ID
        val filteredList = _uiState.value.userInfo.filter {
            it.id != userId
        }
        // Actualizamos el estado
        _uiState.value = UserInfoListUiState(filteredList)
    }
    /**
     * Devuelve un usuario concreto por ID.
     * No modifica el estado.
     */
    fun getUserById(userId: String): UserInfoUiState? {
        return _uiState.value.userInfo.find { it.id == userId }
    }
    /**
     * Carga los datos desde el repositorio.
     * Convierte el modelo de datos al modelo de UI.
     */
    fun loadData(userId: String) {
        repo.readAll({
            _uiState.value = UserInfoListUiState(it.map {
                UserInfoUiState(
                    it.id,
                    it.email,
                    it.user,
                    it.country,
                    it.name,
                    it.surname,

                    )
            }.toList())
        }) {
            //READLL ON ERROR
        }
    }
}