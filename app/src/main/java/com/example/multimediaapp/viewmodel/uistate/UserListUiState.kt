package com.example.multimediaapp.viewmodel.uistate



/**
 * Representa el estado de la pantalla de lista de usuarios.
 *
 * - Se utiliza dentro de un ViewModel (MVVM) para exponer datos a la UI.
 * - Contiene la lista de usuarios, un indicador de carga y posibles errores.
 */
data class UserListUiState(
    /** Lista de usuarios que se mostrarán en la UI */
    val users: List<UserUIState> = emptyList(),
    /** Indica si se están cargando datos (por ejemplo desde un repositorio o API) */
    val isLoading: Boolean = false,
    /** Mensaje de error en caso de fallo al cargar o manipular datos */
    val error: String? = null
)

/**
 * Representa la información de un usuario individual en la UI.
 *
 * - Se crea a partir de datos del repositorio o de una base de datos.
 * - Puede usarse directamente para mostrar información en la pantalla.
 */
data class UserUIState(
    /** ID único del usuario */
    val id: String,
    /** Nombre de usuario */
    val user: String,
    /** Contraseña del usuario (para propósitos de UI, nunca se debería exponer en texto plano en producción) */
    val pass: String

)
