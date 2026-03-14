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

class UserInfoVM(application: Application) : AndroidViewModel(application) {

    private val repo = UsersInfoRepo(application.applicationContext)

    private val _uiState = MutableStateFlow(UserInfoListUiState())
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    fun loadUser(userId: String) {
        // Evitar recargas si el ID ya coincide
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            try {
                // LLAMADA CORRECTA: El método 'read' es suspend y devuelve un Result
                val result = repo.read(userId)

                result.fold(
                    onSuccess = { dto ->
                        dto?.let {
                            val mapped = UserInfoUiState(
                                id = it.id,
                                email = it.email,
                                user = it.user,
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
                        // Aquí podrías actualizar un estado de error en la UI
                    }
                )
            } catch (e: Exception) {
                Log.e("DEBUG_API", "Fallo de red: ${e.message}")
            }
        }
    }
}


