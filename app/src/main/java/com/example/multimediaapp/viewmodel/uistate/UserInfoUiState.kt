package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado de la pantalla de información de usuarios.
 *
 * - Contiene la lista completa de usuarios con sus datos detallados.
 * - Forma parte de la arquitectura MVVM, siendo observado por la UI a través del ViewModel.
 */
data class UserInfoListUiState(
    /** Lista de usuarios con información completa */
    val userInfo: List<UserInfoUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * Representa la información de un solo usuario.
 *
 * - Se utiliza para mostrar datos detallados en la UI.
 * - Puede ser creado a partir de datos del repositorio o base de datos.
 */
data class UserInfoUiState(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val lastName: String = "",
    val country: String = ""
)




