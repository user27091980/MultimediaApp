package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado de la pantalla de registro de usuarios.
 *
 * - Contiene la lista de registros de usuarios.
 * - Se utiliza en un ViewModel siguiendo el patrón MVVM.
 * - La UI observa este estado y se actualiza automáticamente cuando cambia.
 */
data class RegisterListUiState(
    /** Lista de usuarios registrados */
    val regUser: List<RegisterUIState> = ArrayList()
)
/**
 * Representa la información de un solo registro de usuario.
 *
 * - Se utiliza para mostrar y manipular los datos de un usuario en la UI.
 */
data class RegisterUIState(
    /** ID único del registro de usuario */
    val id: String,
    /** Nombre de usuario (login) */
    val user: String,
    /** Contraseña del usuario */
    val pass: String

)