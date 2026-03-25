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
/**
 * UserInfoVM (ViewModel de información de usuario):
 *
 * Este ViewModel gestiona la obtención y el estado de la información
 * de uno o varios usuarios, actuando como intermediario entre la UI
 * y el repositorio de datos (UsersInfoRepo).
 *
 * HEREDA DE AndroidViewModel:
 *
 * - Permite acceder al contexto de la aplicación.
 * - Necesario para inicializar el repositorio.
 *
 * ARQUITECTURA (MVVM):
 *
 * UI (Compose)
 *     ↓
 * UserInfoVM
 *     ↓
 * UsersInfoRepo
 *     ↓
 * Fuente de datos (API / BD)
 *
 * ESTADO:
 *
 * _uiState:
 * - MutableStateFlow interno.
 * - Contiene un objeto UserInfoListUiState con:
 *      • lista de usuarios (userInfo)
 *      • estado de carga (isLoading)
 *      • posibles errores
 *
 * uiState:
 * - StateFlow público (solo lectura).
 * - La UI lo observa con collectAsStateWithLifecycle().
 * - Permite recomposición automática al cambiar los datos.
 *
 * FUNCIÓN PRINCIPAL:
 *
 * loadUser(userId: String):
 *
 * - Carga la información de un usuario específico.
 *
 * OPTIMIZACIÓN:
 * - Antes de hacer la llamada, comprueba si el usuario ya está cargado:
 *
 *      if (_uiState.value.userInfo.any { it.id == userId }) return
 *
 * - Evita llamadas innecesarias al repositorio.
 *
 * FLUJO:
 *
 * 1. Se lanza una corrutina en viewModelScope.
 *
 * 2. Se llama al repositorio:
 *      repo.read(userId)
 *
 * 3. Se maneja el resultado con fold():
 *
 *      onSuccess:
 *          • Se transforma el DTO a UserInfoUiState
 *          • Se actualiza el estado con el usuario obtenido
 *
 *      onFailure:
 *          • Se registra el error en Logcat
 *
 * 4. Manejo adicional de errores con try-catch (fallos de red, etc.).
 *
 * TRANSFORMACIÓN DE DATOS:
 *
 * DTO → UI STATE
 *
 * - Se mapea manualmente:
 *
 *      UserInfoUiState(
 *          id = it.id,
 *          email = it.email,
 *          name = it.name,
 *          lastName = it.lastName,
 *          country = it.country
 *      )
 *
 * - Esto desacopla la capa de datos de la UI.
 *
 * viewModelScope:
 *
 * - Permite ejecutar corrutinas seguras.
 * - Se cancela automáticamente al destruir el ViewModel.
 *
 * BENEFICIOS:
 *
 * - Evita duplicar llamadas a la API.
 * - Mantiene la UI reactiva y actualizada.
 * - Separa claramente modelo de datos y modelo de UI.
 * - Manejo seguro de errores.
 *
 * NOTAS:
 *
 * - Actualmente reemplaza la lista con un solo usuario:
 *      state.copy(userInfo = listOf(mapped))
 *
 * - Se podría ampliar para manejar múltiples usuarios acumulados.
 *
 * - collectAsStateWithLifecycle mejora eficiencia frente a collectAsState.
 */
