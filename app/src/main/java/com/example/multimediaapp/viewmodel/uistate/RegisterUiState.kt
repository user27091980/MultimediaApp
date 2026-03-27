package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario de la pantalla de registro.
 *
 * Esta data class almacena los valores actuales de los campos del formulario de registro,
 * así como información adicional que la UI necesita para mostrarse correctamente.
 *
 * Propiedades:
 * @property user Nombre de usuario o nickname ingresado en el formulario.
 * @property email Correo electrónico ingresado por el usuario.
 * @property pass Contraseña ingresada por el usuario.
 * @property name Nombre real del usuario.
 * @property lastName Apellido del usuario.
 * @property country País ingresado por el usuario.
 * @property errorMessage Mensaje de error que se mostrará en la UI si ocurre alguna validación fallida. Puede ser null si no hay errores.
 * @property isLoading Indica si el formulario está en estado de carga (por ejemplo, mientras se envían los datos al servidor).
 *
 * Uso típico:
 * - Se utiliza dentro de un ViewModel que maneja la lógica del formulario de registro.
 * - La UI observa este estado para mostrar los valores actuales, errores y estado de carga.
 */
data class RegisterFormUiState(

    val email: String = "",
    val passwd: String = "",
    val name: String = "",
    val lastName: String = "",
    val country: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)