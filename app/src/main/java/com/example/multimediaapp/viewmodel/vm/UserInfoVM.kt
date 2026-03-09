package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel que gestiona la información del usuario.
 *
 * - Hereda de AndroidViewModel para poder usar Application / Context.
 * - Expone estado observable (_uiState) para la UI.
 * - Interactúa con UsersInfoRepo, que requiere Context.
 * - Permite precargar un estado inicial (útil para previews o pruebas).
 */
class UserInfoVM(context: Context,
                 initialState: UserInfoListUiState? = null
) : ViewModel(){
    // ESTADO OBSERVABLE
    // MutableStateFlow privado para mantener el estado interno
    private val _uiState = MutableStateFlow(initialState ?: UserInfoListUiState())
    // Exponemos un StateFlow inmutable para la UI
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()
    // REPOSITORIO
    // Instancia del repositorio de usuarios
    // Nota: si UsersInfoRepo requiere contexto, asegurarse de pasarlo en el constructor
    private val repo: UsersInfoRepo = UsersInfoRepo(context)
    // CARGA DE DATOS
    /**
     * Carga los datos de un usuario por ID.
     *
     * - Primero verifica si el usuario ya está en _uiState para evitar recarga innecesaria.
     * - Llama al repositorio para obtener datos reales.
     * - Actualiza el estado con la información del usuario.
     *
     * @param userId ID del usuario a cargar.
     */
    fun loadUser(userId: String) {
        // Si ya tenemos el usuario, no hacemos nada
        if (_uiState.value.userInfo.any { it.id == userId }) return // ya cargado
        // Llamada al repositorio para leer el usuario
        repo.read(userId, { data ->
            data?.let {
                // Mapear el DTO recibido a la UIState
                val mapped = UserInfoUiState(
                    id = it.id,
                    email = it.email,
                    user = it.user,
                    country = it.country,
                    name = it.name,
                    surname = it.surname
                )
                // Actualizar el estado con el nuevo usuario
                _uiState.value = UserInfoListUiState(userInfo = listOf(mapped))
            }
        }) {
            // manejar error
        }
    }
}
