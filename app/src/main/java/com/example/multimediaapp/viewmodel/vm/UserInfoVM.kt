package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel que gestiona la información del usuario.
 * Puede recibir un estado inicial para previews o pruebas.
 */
class UserInfoVM(initialState: UserInfoListUiState? = null) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState ?: UserInfoListUiState())
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    private val repo: UsersInfoRepo = UsersInfoRepo()

    /**
     * Carga los datos de un usuario por ID.
     * Si ya hay datos (como en preview), no hace nada.
     */
    fun loadData(userId: String) {
        if (_uiState.value.userInfo.any { it.id == userId }) return // ya cargado

        // En ejecución real, traemos datos del repositorio
        repo.readAll({ list ->
            val mapped = list.map { data ->
                UserInfoUiState(
                    id = data.id,
                    email = data.email,
                    user = data.user,
                    country = data.country,
                    name = data.name,
                    surname = data.surname
                )
            }
            _uiState.value = UserInfoListUiState(mapped)
        }) {
            // manejar error de lectura si quieres
        }
    }
}
