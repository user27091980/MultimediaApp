package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * RegisterVM:
 * ViewModel encargado de la pantalla de registro.
 *
 * Gestiona el estado reactivo del formulario, validación de campos
 * y la comunicación con UsersInfoRepo para registrar al usuario.
 *
 * Usamos AndroidViewModel para poder obtener el contexto de la app
 * necesario para SessionManager y Repositorios.
 */
/**
 * ViewModel encargado de la pantalla de registro de usuario.
 *
 * Gestiona:
 * - El estado reactivo del formulario
 * - La validación de los campos
 * - La comunicación con el repositorio de usuarios
 *
 * Utiliza [AndroidViewModel] para disponer del contexto de la aplicación,
 * necesario para operaciones relacionadas con almacenamiento o sesión.
 *
 * @property application Contexto de la aplicación.
 */
class RegisterVM(application: Application) : AndroidViewModel(application) {

    /**
     * Repositorio encargado de las operaciones de usuario.
     */
    private val repo = UsersInfoRepo(application.applicationContext)

    /**
     * Estado interno del formulario.
     */
    private val _uiState = MutableStateFlow(
        RegisterFormUiState(
            errorMessage = null,
            lastName = "",
            email = "",
            pass = "",
            name = "",
            country = "",
            isLoading = false
        )
    )

    /**
     * Estado observable del formulario.
     */
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el email en el estado.
     *
     * @param newEmail Nuevo valor del email.
     */
    fun onEmailChange(newEmail: String) =
        _uiState.update { it.copy(email = newEmail, errorMessage = null) }

    /**
     * Actualiza la contraseña en el estado.
     *
     * @param newPass Nueva contraseña.
     */
    fun onPassChange(newPass: String) =
        _uiState.update { it.copy(pass = newPass, errorMessage = null) }

    /**
     * Actualiza el nombre en el estado.
     *
     * @param newName Nuevo nombre.
     */
    fun onNameChange(newName: String) =
        _uiState.update { it.copy(name = newName, errorMessage = null) }

    /**
     * Actualiza el apellido en el estado.
     *
     * @param newLastname Nuevo apellido.
     */
    fun onLastNameChange(newLastname: String) =
        _uiState.update { it.copy(lastName = newLastname, errorMessage = null) }

    /**
     * Actualiza el país en el estado.
     *
     * @param newCountry Nuevo país.
     */
    fun onCountryChange(newCountry: String) =
        _uiState.update { it.copy(country = newCountry, errorMessage = null) }

    /**
     * Valida los campos del formulario.
     *
     * Si los datos son válidos, se ejecuta el registro del usuario.
     * En caso contrario, se actualiza [RegisterFormUiState.errorMessage].
     *
     * @param onSuccess Callback que se ejecuta si el registro es exitoso.
     */
    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value

        val error = when {
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Email inválido"
            state.pass.length < 4 -> "La contraseña debe tener al menos 4 caracteres"
            state.name.isBlank() || state.lastName.isBlank() -> "Nombre y apellido son obligatorios"
            else -> null
        }

        if (error != null) {
            _uiState.update { it.copy(errorMessage = error) }
        } else {
            registerUser(
                email = state.email,
                pass = state.pass,
                name = state.name,
                lastName = state.lastName,
                country = state.country,
                onSuccess = onSuccess
            )
        }
    }

    /**
     * Realiza el registro del usuario en el servidor.
     *
     * Gestiona el estado de carga y maneja posibles errores.
     *
     * @param email Email del usuario.
     * @param pass Contraseña del usuario.
     * @param name Nombre del usuario.
     * @param lastName Apellido del usuario.
     * @param country País del usuario.
     * @param onSuccess Callback que se ejecuta si el registro es exitoso.
     */
    private fun registerUser(
        email: String,
        pass: String,
        name: String,
        lastName: String,
        country: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = repo.register(
                email = email,
                name = name,
                pass = pass,
                country = country,
                lastName = lastName
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                },
                onFailure = { ex ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = ex.message)
                    }
                }
            )
        }
    }
}
/**
 * RegisterVM (ViewModel de registro de usuario):
 *
 * Este ViewModel gestiona toda la lógica de la pantalla de registro,
 * incluyendo el estado del formulario, validación de datos y comunicación
 * con el repositorio para registrar un usuario.
 *
 * HEREDA DE AndroidViewModel:
 *
 * - Permite acceder al contexto de la aplicación (Application).
 * - Útil cuando necesitas recursos del sistema (SharedPreferences, DB, etc.).
 *
 * ARQUITECTURA (MVVM):
 *
 * UI (Compose)
 *     ↓
 * RegisterVM
 *     ↓
 * UsersInfoRepo
 *     ↓
 * Fuente de datos (API / BD / SessionManager)
 *
 * ESTADO:
 *
 * _uiState:
 * - MutableStateFlow interno.
 * - Contiene el estado completo del formulario:
 *      • email
 *      • contraseña
 *      • nombre
 *      • apellido
 *      • país
 *      • loading
 *      • errores
 *
 * uiState:
 * - StateFlow público (solo lectura).
 * - La UI lo observa con collectAsState().
 *
 * ACTUALIZACIÓN DE CAMPOS:
 *
 * onEmailChange()
 * onPassChange()
 * onNameChange()
 * onLastNameChange()
 * onCountryChange()
 *
 * - Cada función:
 *      • actualiza el valor correspondiente
 *      • limpia mensajes de error
 *      • usa copy() para mantener inmutabilidad
 *
 * VALIDACIÓN:
 *
 * validateFields():
 *
 * - Comprueba:
 *      • Email válido (Patterns.EMAIL_ADDRESS)
 *      • Contraseña mínima (>= 4 caracteres)
 *      • Nombre y apellido no vacíos
 *
 * - Si hay error:
 *      → actualiza errorMessage en el estado
 *
 * - Si todo es correcto:
 *      → llama a registerUser()
 *
 * REGISTRO DE USUARIO:
 *
 * registerUser():
 *
 * - Ejecuta una corrutina en viewModelScope
 * - Marca la UI como cargando (isLoading = true)
 * - Llama al repositorio:
 *      repo.register(...)
 *
 * - Maneja el resultado con fold():
 *
 *      onSuccess:
 *          • desactiva loading
 *          • ejecuta callback onSuccess()
 *
 *      onFailure:
 *          • desactiva loading
 *          • muestra error en UI
 *
 * viewModelScope:
 * - Scope de corrutinas ligado al ciclo de vida del ViewModel.
 * - Evita fugas de memoria.
 *
 * BENEFICIOS:
 *
 * - Separación clara entre UI y lógica de negocio.
 * - Validación centralizada.
 * - Manejo de estado reactivo.
 * - Código limpio y reutilizable.
 *
 * NOTAS:
 *
 * - Se usa copy() para mantener inmutabilidad del estado.
 * - StateFlow permite recomposición automática en Compose.
 * - El callback onSuccess permite desacoplar la navegación del ViewModel.
 */