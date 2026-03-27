package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UserInfoRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la información del usuario.
 */
class UserInfoVM(application: Application) : AndroidViewModel(application) {

    // Repositorio con la API de Retrofit
    private val repo = UserInfoRepo(RetrofitModule.userInfoApi)

    // Estado interno de la UI
    private val _uiState = MutableStateFlow(UserInfoListUiState())
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    /**
     * Carga la información del usuario por su ID.
     */
    fun loadUser(userId: String) {
        // Evita recargar si ya existe
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            try {
                // Llamada al repositorio
                val userDto = repo.getUser(userId)

                val mapped = UserInfoUiState(
                    id = userDto.id,
                    user = userDto.user,
                    email = userDto.email,
                    name = userDto.name,
                    lastName = userDto.lastName,
                    country = userDto.country
                )

                // Añade o reemplaza el usuario en la lista
                _uiState.update { state ->
                    state.copy(userInfo = state.userInfo.filter { it.id != userId } + mapped)
                }

            } catch (e: Exception) {
                Log.e("DEBUG_API", "Error al cargar usuario: ${e.message}", e)
            }
        }
    }
}