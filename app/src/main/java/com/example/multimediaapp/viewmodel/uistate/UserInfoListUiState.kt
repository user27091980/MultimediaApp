package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado de la pantalla de información de usuarios.
 *
 * - Contiene la lista completa de usuarios con sus datos detallados.
 * - Forma parte de la arquitectura MVVM, siendo observado por la UI a través del ViewModel.
 */
data class UserInfoListUiState(
    /** Lista de usuarios con información completa */
    val userInfo: List<UserInfoUiState> = ArrayList()
)

/**
 * Representa la información de un solo usuario.
 *
 * - Se utiliza para mostrar datos detallados en la UI.
 * - Puede ser creado a partir de datos del repositorio o base de datos.
 */
data class UserInfoUiState(
    /** ID único del usuario */
    val id: String,
    /** Correo electrónico del usuario */
    val email: String,
    /** Nombre de usuario (login) */
    val user: String,
    /** País del usuario */
    val country: String,
    /** Nombre de pila del usuario */
    val name: String,
    /** Apellido del usuario */
    val surname: String
)



