package com.example.multimediaapp.viewmodel.uistate
/**
 * Representa el estado de la pantalla de configuración (Settings).
 *
 * - Contiene información que la UI necesita para renderizar la pantalla de ajustes.
 * - Se utiliza dentro de un ViewModel, siguiendo el patrón MVVM.
 */
data class SettingsUiState(
    /** Indica si el modo oscuro está activado o no */
    val darkMode: Boolean = false,
    /** Versión de la aplicación, útil para mostrar en la UI o para registro */
    val appVersion: String = "1.0"
)