package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario de la pantalla de configuración (Settings).
 *
 * Esta data class almacena los valores que la UI necesita para mostrarse correctamente,
 * y sirve como fuente única de verdad para la pantalla de ajustes, siguiendo el patrón MVVM.
 *
 * Propiedades:
 * @property darkMode Indica si el modo oscuro está activado. La UI puede observar este valor
 * y actualizar los elementos visuales en consecuencia.
 * @property appVersion Versión actual de la aplicación. Útil para mostrar en la pantalla de
 * configuración o para fines de registro y depuración.
 *
 * Uso típico:
 * - Se utiliza dentro del ViewModel de Settings para mantener la consistencia del estado.
 * - La UI observa este estado y se vuelve a componer automáticamente cuando alguno de los
 * valores cambia.
 *
 * Ejemplo:
 * ```
 * val uiState by settingsViewModel.uiState.collectAsState()
 * Text("Versión: ${uiState.appVersion}")
 * Switch(checked = uiState.darkMode, onCheckedChange = { settingsViewModel.toggleDarkMode() })
 * ```
 */
data class SettingsUiState(
    val darkMode: Boolean = false,
    val appVersion: String = "1.0"
)