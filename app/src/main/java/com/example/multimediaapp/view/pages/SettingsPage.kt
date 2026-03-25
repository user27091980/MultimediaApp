package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.R
import com.example.multimediaapp.viewmodel.vm.SettingsVM

/**
 * Pantalla de configuración de la app.
 * Permite cambiar preferencias como el modo oscuro y mostrar información básica de la app.
 */
@Composable
fun SettingsScreen(vm: SettingsVM = viewModel()) {
    // Estado local para el switch de modo oscuro
    val uiState by vm.uiState.collectAsState()// Columna principal que organiza todos los elementos de la pantalla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)// Ocupa todo el espacio disponible
            .padding(16.dp)// Margen interno de 16dp en todos los lados
    ) {
        // Título de la pantalla
        Text(
            text = "configuración",
            style = MaterialTheme.typography.headlineMedium// Estilo de título principal de Material3
        )

        Spacer(modifier = Modifier.height(24.dp))// Separación vertical de 24dp

        // Sección de apariencia
        Text(
            "Apariencia", style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )// Subtítulo
        // Switch para el modo oscuro
        SettingSwitch(
            title = "Modo óscuro",// Texto del switch
            checked = uiState.darkMode,// Estado del switch
            onCheckedChange = { vm.onDarkModeChange(it) },
        )
        Spacer(modifier = Modifier.height(24.dp))// Separación vertical de 24dp

        // Información sobre la versión de la app
        Text(
            text = stringResource(R.string.version),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))


    }
}

/**
 * Componente genérico para un switch con título.
 * @param title Texto que aparece a la izquierda del switch
 * @param checked Estado del switch (true = activado)
 * @param onCheckedChange Callback que se ejecuta al cambiar el estado
 */
@Composable
fun SettingSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween// Distribuye el texto y el switch a los extremos
    ) {
        Text(title)// Título a la izquierda
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

/**
 * Preview de la pantalla de configuración.
 * Permite ver cómo se renderiza en el editor de Compose sin ejecutar la app.
 */
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsScreen()
}
/*
 * Pantalla de configuración de la aplicación.
 *
 * OBJETIVO:
 *
 * - Permitir al usuario ajustar preferencias de la app.
 * - Mostrar información básica como la versión.
 * - Gestionar opciones visuales (como modo oscuro).
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - SettingsVM gestiona el estado y la lógica.
 * - La UI observa el estado y responde a cambios.
 *
 * FLUJO DE DATOS:
 *
 * 1. Se obtiene el estado desde el ViewModel:
 *      val uiState by vm.uiState.collectAsState()
 *
 * 2. El switch refleja el estado:
 *      checked = uiState.darkMode
 *
 * 3. Cuando el usuario cambia el switch:
 *      vm.onDarkModeChange(it)
 *
 * ESTRUCTURA DE LA UI:
 *
 * Column principal:
 * - Ocupa toda la pantalla (fillMaxSize)
 * - Aplica fondo con MaterialTheme
 * - Añade padding interno de 16dp
 *
 * SECCIONES:
 *
 * 1. Título:
 *    - "configuración"
 *
 * 2. Sección de apariencia:
 *    - Subtítulo "Apariencia"
 *    - Switch para activar/desactivar modo oscuro
 *
 * 3. Información:
 *    - Muestra la versión de la app desde strings.xml
 *
 * COMPONENTES:
 *
 * SettingSwitch:
 * - Componente reutilizable.
 * - Muestra:
 *   - Texto a la izquierda
 *   - Switch a la derecha
 * - Usa Row con:
 *     Arrangement.SpaceBetween
 *   para separar ambos elementos.
 *
 * Switch:
 * - Componente de Material3.
 * - Permite alternar entre:
 *     true (activado)
 *     false (desactivado)
 *
 * GESTIÓN DEL MODO OSCURO:
 *
 * uiState.darkMode:
 * - Guarda el estado actual.
 *
 * vm.onDarkModeChange(it):
 * - Actualiza el estado en el ViewModel.
 *
 * RECURSOS:
 *
 * stringResource(R.string.version):
 * - Obtiene textos desde res/values/strings.xml.
 * - Permite internacionalización.
 *
 * PREVIEW:
 *
 * SettingsPreview:
 * - Permite visualizar la UI en Android Studio.
 * - No necesita ejecutar la app.
 *
 * BENEFICIOS:
 *
 * - UI simple y clara.
 * - Separación de responsabilidades.
 * - Reutilización del componente SettingSwitch.
 * - Estado centralizado en el ViewModel.
 *
 * NOTAS IMPORTANTES:
 *
 * - collectAsState():
 *   Hace observable el estado del ViewModel.
 *
 * - Row:
 *   Permite organizar elementos en horizontal.
 *
 * - Spacer:
 *   Añade separación entre secciones.
 *
 * - MaterialTheme:
 *   Garantiza coherencia visual en toda la app.
 */