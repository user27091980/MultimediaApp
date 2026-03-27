package com.example.multimediaapp.viewmodel.uistate

/**
 * Estado de la pantalla de información de usuarios (User Info Screen).
 *
 * Contiene todos los datos necesarios para que la UI muestre la lista de usuarios
 * y sus detalles. Forma parte del patrón MVVM, siendo observado por la UI a través del ViewModel.
 *
 * Propiedades:
 * @property userInfo Lista de usuarios con información completa. Cada elemento es de tipo [UserInfoUiState].
 * @property isLoading Indica si la pantalla se encuentra cargando datos. Útil para mostrar spinners o placeholders.
 * @property error Mensaje de error opcional en caso de fallos al obtener los datos.
 *
 * Uso típico:
 * - El ViewModel expone este estado a la UI usando StateFlow o LiveData.
 * - La UI observa los cambios y se recompone automáticamente cuando cambia la lista, el estado de carga o aparece un error.
 */
data class UserInfoListUiState(
    val userInfo: List<UserInfoUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * Representa la información de un solo usuario.
 *
 * Esta clase se utiliza para mostrar los detalles de un usuario específico
 * dentro de la pantalla de User Info.
 *
 * Propiedades:
 * @property id Identificador único del usuario.
 * @property user Nombre de usuario o alias.
 * @property email Correo electrónico del usuario.
 * @property name Nombre real del usuario.
 * @property lastName Apellido del usuario.
 * @property country País de residencia del usuario.
 *
 * Uso típico:
 * - Cada elemento de [UserInfoListUiState.userInfo] es de este tipo.
 * - Se puede mapear desde un DTO recibido del repositorio o base de datos.
 */
data class UserInfoUiState(
    val id: String = "",
    val user: String = "",
    val email: String = "",
    val name: String = "",
    val lastName: String = "",
    val country: String = ""
)