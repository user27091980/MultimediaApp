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
 * UserInfoVM:
 *
 * ViewModel encargado de manejar la información del usuario.
 * - Extiende AndroidViewModel para tener acceso al contexto de la aplicación.
 * - Expone un StateFlow observable con la lista de usuarios cargados.
 * - Gestiona la llamada a UsersInfoRepo y mapea UsersInfoDTO a UserInfoUiState.
 */
class UserInfoVM(application: Application) : AndroidViewModel(application) {

    // Repositorio para obtener la información de usuarios
    private val repo = UsersInfoRepo(application.applicationContext)

    // Estado interno mutable
    private val _uiState = MutableStateFlow(UserInfoListUiState())
    // Exposición pública como solo lectura para la UI
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    /**
     * Carga la información de un usuario por su ID.
     *
     * @param userId Identificador del usuario que queremos cargar.
     *
     * Evita recargar si el usuario ya está en la lista.
     */
    fun loadUser(userId: String) {
        // Evitamos llamadas redundantes si ya tenemos el usuario cargado
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            try {
                // Llamada suspend al repositorio; devuelve Result<UsersInfoDTO?>
                val result = repo.read(userId)

                result.fold(
                    onSuccess = { dto ->
                        // Solo actualizamos el estado si el DTO no es nulo
                        dto?.let {
                            val mapped = UserInfoUiState(
                                id = it.id,
                                email = it.email,

                                name = it.name,
                                lastName = it.lastName,
                                country = it.country
                            )
                            // Actualizamos el StateFlow de forma inmutable
                            _uiState.update { state ->
                                state.copy(userInfo = listOf(mapped))
                            }
                        }
                    },
                    onFailure = { error ->
                        Log.e("DEBUG_API", "Error al cargar usuario: ${error.message}")
                        // Aquí podrías actualizar un estado de error en la UI si quieres mostrarlo
                    }
                )
            } catch (e: Exception) {
                Log.e("DEBUG_API", "Fallo de red: ${e.message}", e)
            }
        }
    }
}


