package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la información del usuario.
 *
 * Expone el estado de la UI mediante [StateFlow] y actúa como intermediario
 * entre la capa de datos ([UsersInfoRepo]) y la interfaz de usuario.
 *
 * Funciones principales:
 * - Cargar información de usuario
 * - Transformar datos (DTO → UI State)
 * - Gestionar el estado reactivo
 *
 * @property application Contexto de la aplicación necesario para el repositorio.
 */
class UserInfoVM(application: Application) : AndroidViewModel(application) {

    /**
     * Repositorio encargado de obtener la información de usuarios.
     */
    private val repo = UsersInfoRepo(application.applicationContext)

    /**
     * Estado interno mutable de la lista de usuarios.
     */
    private val _uiState = MutableStateFlow(UserInfoListUiState())

    /**
     * Estado observable expuesto a la UI.
     */
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    /**
     * Function that loads user information by its identifier.
     *
     * Evita llamadas redundantes si el usuario ya está cargado en el estado.
     *
     * @param userId Identificador del usuario a cargar.
     */
    fun loadUser(userId: String) {
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            try {
                val result = repo.read(userId)

                result.fold(
                    onSuccess = { dto ->
                        dto?.let {
                            val mapped = UserInfoUiState(
                                id = it.id,
                                email = it.email,
                                name = it.name,
                                lastName = it.lastName,
                                country = it.country
                            )

                            _uiState.update { state ->
                                state.copy(userInfo = listOf(mapped))
                            }
                        }
                    },
                    onFailure = { error ->
                        Log.e("DEBUG_API", "Error al cargar usuario: ${error.message}")
                    }
                )
            } catch (e: Exception) {
                Log.e("DEBUG_API", "Fallo de red: ${e.message}", e)
            }
        }
    }
}

