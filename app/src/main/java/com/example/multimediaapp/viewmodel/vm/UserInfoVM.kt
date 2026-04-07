package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UserInfoRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la información de usuarios.
 *
 * Funciones principales:
 * - Cargar datos de un usuario específico desde el repositorio ([UserInfoRepo]).
 * - Mantener un estado reactivo de la lista de usuarios ([UserInfoListUiState]).
 * - Actualizar la UI automáticamente cuando cambian los datos.
 *
 * Patrón MVVM:
 * - La UI observa [uiState] y se recompondrá automáticamente al recibir nuevos datos.
 * - La lógica de obtención y transformación de datos queda encapsulada en este ViewModel.
 *
 * Uso:
 * - Llamar [loadUser] pasando el ID de un usuario para obtener su información.
 * - La lista de usuarios en [uiState.userInfo] se actualiza de forma incremental.
 */
class UserInfoVM(
    application: Application,
    private val dataStore: DataStoreManager
) : AndroidViewModel(application) {

    /** Repositorio que maneja la comunicación con la API de usuarios */
    private val repo = UserInfoRepo(RetrofitModule.userInfoApi)

    /** Estado interno mutable que almacena la lista de usuarios */
    private val _uiState = MutableStateFlow(UserInfoListUiState())

    /** Estado observable por la UI */
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()

    init {
        // Al iniciar, buscamos si hay un usuario en sesión y cargamos sus datos reales del repo
        viewModelScope.launch {
            dataStore.userFlow.collect { userSession ->
                userSession?.id?.let { id ->
                    loadUser(id)
                }
            }
        }
    }
    /**
     * Carga la información de un usuario por su ID.
     *
     * - Verifica si el usuario ya existe en el estado para evitar recargas innecesarias.
     * - Llama al repositorio para obtener los datos del usuario.
     * - Transforma los datos a [UserInfoUiState].
     * - Actualiza [_uiState] agregando o reemplazando el usuario en la lista.
     * - En caso de error, registra el error en Logcat.
     *
     * @param userId ID del usuario que se desea cargar.
     */
    fun loadUser(userId: String) {
        // Evita recargar si ya existe
        if (_uiState.value.userInfo.any { it.id == userId }) return

        viewModelScope.launch {
            try {
                // Llamada al repositorio para obtener datos del usuario
                val userDto = repo.getUserInfo(userId)

                // Mapeo a UI State
                val mapped = UserInfoUiState(
                    id = userDto.id,
                    email = userDto.email,
                    name = userDto.name,
                    lastName = userDto.lastName,
                    country = userDto.country
                )

                // Actualiza la lista de usuarios en el estado
                _uiState.update { state ->
                    state.copy(
                        userInfo = state.userInfo.filter { it.id != userId } + mapped
                    )
                }

            } catch (e: Exception) {
                // Log del error para depuración
                Log.e("DEBUG_API", "Error al cargar usuario: ${e.message}", e)
            }
        }
    }
    fun logout() {
        viewModelScope.launch {
            dataStore.logout()
        }
    }
}