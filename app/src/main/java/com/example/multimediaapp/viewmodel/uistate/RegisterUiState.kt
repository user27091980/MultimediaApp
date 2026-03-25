package com.example.multimediaapp.viewmodel.uistate

/*
 * Estado de la pantalla de registro de usuario para el formulario.
 *
 * Contiene los valores actuales de los campos y un posible mensaje de error.
 */
data class RegisterFormUiState(
    val user: String = "",
    val email: String = "",
    val pass: String = "",
    val name: String = "",
    val country: String = "",
    val errorMessage: String? = null,
    val lastName: String,
    val isLoading: Boolean
)
/*
 * RegisterFormUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado del formulario de registro de usuario.
 * Se utiliza en el patrón MVVM para gestionar de forma reactiva
 * todos los campos del formulario y sus posibles errores.
 *
 * OBJETIVO:
 *
 * - Mantener los datos introducidos por el usuario.
 * - Controlar el estado de carga durante el registro.
 * - Mostrar mensajes de error en la UI.
 *
 * PROPIEDADES:
 *
 * user:
 * - Nombre de usuario o nickname.
 *
 * email:
 * - Correo electrónico introducido en el formulario.
 *
 * pass:
 * - Contraseña del usuario.
 *
 * name:
 * - Nombre real del usuario.
 *
 * country:
 * - País del usuario.
 *
 * lastName:
 * - Apellido del usuario.
 *
 * errorMessage:
 * - Mensaje de error que se muestra en la UI.
 * - null → no hay error.
 * - String → se muestra como mensaje informativo.
 *
 * isLoading:
 * - Indica si el registro está en proceso.
 * - true → se puede mostrar un indicador de carga.
 * - false → el formulario está disponible.
 *
 * IMPORTANTE:
 *
 * - lastName e isLoading NO tienen valor por defecto.
 *   → Esto obliga a proporcionarlos al crear el estado.
 *
 * FLUJO DE USO:
 *
 * 1. El usuario introduce datos en el formulario.
 *
 * 2. El ViewModel actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa los cambios:
 *      val state by viewModel.uiState.collectAsState()
 *
 * 4. La UI reacciona automáticamente:
 *
 *    - Mostrar errores:
 *        state.errorMessage?.let { Text(it) }
 *
 *    - Mostrar loading:
 *        if (state.isLoading)
 *
 *    - Refrescar campos:
 *        TextField(value = state.email, ...)
 *
 * BENEFICIOS:
 *
 * - Centraliza todos los datos del formulario.
 * - Permite UI reactiva.
 * - Facilita validación y manejo de errores.
 * - Código limpio y mantenible.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Se actualiza mediante:
 *      copy()
 *
 * NOTAS:
 *
 * - Este estado se suele usar junto a un ViewModel como:
 *      RegisterVM
 *
 * - Es recomendable definir valores por defecto para todos los campos
 *   para evitar errores de inicialización.
 */