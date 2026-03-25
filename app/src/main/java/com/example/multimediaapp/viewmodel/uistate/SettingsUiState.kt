package com.example.multimediaapp.viewmodel.uistate

/*
 * Representa el estado de la pantalla de configuración (Settings).
 *
 * - Contiene información que la UI necesita para renderizar la pantalla de ajustes.
 * - Se utiliza dentro de un ViewModel, siguiendo el patrón MVVM.
 */
data class SettingsUiState(
    // Indica si el modo oscuro está activado o no
    val darkMode: Boolean = false,
    //Versión de la aplicación, útil para mostrar en la UI o para registro
    val appVersion: String = "1.0"
)
/*
 * SettingsUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado de la pantalla de configuración (Settings).
 * Se utiliza dentro del patrón MVVM para exponer a la UI los valores
 * que necesita para renderizar la pantalla de ajustes.
 *
 * OBJETIVO:
 *
 * - Gestionar las preferencias del usuario.
 * - Mantener el estado de opciones como el modo oscuro.
 * - Mostrar información de la aplicación (como la versión).
 *
 * PROPIEDADES:
 *
 * darkMode:
 * - Indica si el modo oscuro está activado.
 * - true → modo oscuro activado.
 * - false → modo claro.
 * - Se utiliza normalmente para cambiar el tema de la app.
 *
 * appVersion:
 * - Representa la versión actual de la aplicación.
 * - Se puede mostrar en la UI (por ejemplo, en "Acerca de").
 * - Útil también para logs o control de versiones.
 *
 * FLUJO DE USO:
 *
 * 1. La UI observa el estado:
 *      val state by settingsVM.uiState.collectAsState()
 *
 * 2. El usuario interactúa:
 *      - Activa/desactiva el modo oscuro
 *
 * 3. El ViewModel actualiza el estado:
 *      uiState = uiState.copy(darkMode = true)
 *
 * 4. La UI se actualiza automáticamente:
 *      - Cambia el tema
 *      - Se reflejan los nuevos valores
 *
 * BENEFICIOS:
 *
 * - Estado centralizado y sencillo.
 * - UI reactiva.
 * - Fácil de mantener y extender.
 * - Separación clara entre lógica y presentación.
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
 * - darkMode puede conectarse con:
 *     DataStore, SharedPreferences o base de datos.
 *
 * - appVersion normalmente se obtiene de:
 *     BuildConfig.VERSION_NAME o recursos de la app.
 */