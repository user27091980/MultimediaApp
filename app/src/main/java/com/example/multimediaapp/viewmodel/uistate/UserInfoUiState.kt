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

/*
 * UserInfoListUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado completo de la pantalla que muestra la información de usuarios.
 * Se utiliza dentro del patrón MVVM para exponer los datos de forma reactiva a la UI.
 *
 * OBJETIVO:
 *
 * - Mantener una lista de usuarios.
 * - Gestionar estados de carga.
 * - Mostrar posibles errores.
 *
 * PROPIEDADES:
 *
 * userInfo:
 * - Lista de usuarios (UserInfoUiState).
 * - Contiene la información detallada de cada usuario.
 *
 * isLoading:
 * - Indica si los datos se están cargando.
 * - true → se puede mostrar un indicador de carga.
 *
 * error:
 * - Mensaje de error en caso de fallo.
 * - null → no hay error.
 *
 * FLUJO DE USO:
 *
 * 1. El ViewModel obtiene los datos (API, base de datos, etc.).
 *
 * 2. Actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa los cambios:
 *      val state by viewModel.uiState.collectAsState()
 *
 * 4. La UI reacciona:
 *
 *    - Mostrar loading si isLoading = true
 *    - Mostrar error si error != null
 *    - Mostrar lista si hay datos
 *
 * BENEFICIOS:
 *
 * - Estado centralizado.
 * - UI reactiva.
 * - Fácil mantenimiento.
 * - Separación clara de responsabilidades.
 */


/*
 * UserInfoUiState:
 *
 * CLASE DE ESTADO (ITEM INDIVIDUAL)
 *
 * Representa la información de un solo usuario.
 * Se utiliza para mostrar datos en componentes de UI como tarjetas o listas.
 *
 * OBJETIVO:
 *
 * - Modelar los datos de un usuario.
 * - Servir como elemento dentro de listas (UserInfoListUiState).
 *
 * PROPIEDADES:
 *
 * id:
 * - Identificador único del usuario.
 *
 * email:
 * - Correo electrónico del usuario.
 *
 * name:
 * - Nombre del usuario.
 *
 * lastName:
 * - Apellido del usuario.
 *
 * country:
 * - País del usuario.
 *
 * FLUJO DE USO:
 *
 * 1. Los datos se obtienen desde un repositorio o API.
 *
 * 2. Se transforman en objetos UserInfoUiState.
 *
 * 3. Se añaden a la lista:
 *      userInfo: List<UserInfoUiState>
 *
 * 4. La UI los muestra:
 *      UserCardComponent(...)
 *
 * BENEFICIOS:
 *
 * - Modelo claro y estructurado.
 * - Fácil de mapear desde datos reales.
 * - Reutilizable en diferentes pantallas.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Se actualiza usando:
 *      copy()
 *
 * NOTA:
 *
 * - Este modelo suele construirse a partir de:
 *     DTOs, entidades o respuestas de API.
 */



