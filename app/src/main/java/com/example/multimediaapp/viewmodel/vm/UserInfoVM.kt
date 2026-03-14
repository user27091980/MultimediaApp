package com.example.multimediaapp.viewmodel.vm


import android.app.Application
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

class UserInfoVM(application: Application) : AndroidViewModel(application) {

    // Repositorio real que usa el contexto de la aplicación
    private val repo = UsersInfoRepo(application.applicationContext)

    private val _uiState = MutableStateFlow(UserInfoListUiState())
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    /**
     * Carga los datos del usuario usando Corrutinas y el nuevo Repositorio.
     */
    fun loadUser(userId: String) {
        // Evitar recargas si el ID ya coincide con el cargado
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            // 1. Podrías añadir un estado de carga aquí si tu UiState lo permite

            // 2. Llamada al repo (usando el método read que devuelve Result o DTO)
            // Nota: He adaptado la llamada al estilo de tu nuevo UsersInfoRepo con Retrofit
            try {
                // Si tu repo.read ahora es suspend, se usa así:
                repo.read(userId,
                    onSuccess = { data ->
                        data?.let {
                            val mapped = UserInfoUiState(
                                id = it.id,
                                email = it.email,
                                user = it.user,
                                name = it.name,
                                lastName = it.lastName,
                                country = it.country
                            )
                            _uiState.update { it.copy(userInfo = listOf(mapped)) }
                        }
                    },
                    onError = {
                        // Manejar error de carga
                    }
                )
            } catch (e: Exception) {
                // Manejar excepción de red
            }
        }
    }
}


