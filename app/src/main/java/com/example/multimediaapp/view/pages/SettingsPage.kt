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
 * SettingsScreen:
 *
 * Pantalla de configuración de la aplicación.
 *
 * Funcionalidades principales:
 * - Permite activar o desactivar el modo oscuro.
 * - Muestra información básica como la versión de la app.
 *
 * @param vm ViewModel que maneja el estado de la pantalla de configuración.
 */
@Composable
fun SettingsScreen(vm: SettingsVM = viewModel()) {

    // Observa el estado de la UI desde el ViewModel
    val uiState by vm.uiState.collectAsState()

    // Columna principal que organiza todos los elementos verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .background(MaterialTheme.colorScheme.background) // Fondo según tema
            .padding(16.dp) // Margen interno
    ) {
        // Título principal de la pantalla
        Text(
            text = "Configuración",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp)) // Separación vertical

        // Sección de apariencia
        Text(
            "Apariencia",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Switch para activar o desactivar el modo oscuro
        SettingSwitch(
            title = "Modo oscuro",
            checked = uiState.darkMode,
            onCheckedChange = { vm.onDarkModeChange(it) }
        )

        Spacer(modifier = Modifier.height(24.dp)) // Separación vertical

        // Información de versión de la app
        Text(
            text = stringResource(R.string.version),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * SettingSwitch:
 *
 * Componente reutilizable para mostrar un switch con un título.
 *
 * @param title Texto que aparece a la izquierda del switch.
 * @param checked Estado actual del switch (true = activado, false = desactivado).
 * @param onCheckedChange Callback que se ejecuta al cambiar el estado del switch.
 */
@Composable
fun SettingSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Distribuye texto y switch a los extremos
    ) {
        Text(title) // Título del switch
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

/**
 * Vista previa de la pantalla de configuración.
 *
 * Permite ver cómo se renderiza en el editor de Compose sin ejecutar la app.
 */
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsScreen()
}

/**
 * Notas / teoría:
 *
 * 1. **Box / Column / Row**: Contenedores para organizar elementos vertical u horizontalmente.
 * 2. **Modifier.fillMaxSize() / fillMaxWidth() / padding()**: Ajustan tamaño y márgenes de los elementos.
 * 3. **MaterialTheme.colorScheme**: Permite aplicar los colores definidos en el tema de la app.
 * 4. **Switch**: Componente de selección binaria (on/off) de Jetpack Compose.
 * 5. **collectAsState()**: Observa un StateFlow del ViewModel y convierte su valor en un estado Compose observable.
 * 6. **@Preview**: Permite previsualizar la UI en el IDE sin necesidad de ejecutar la app.
 * 7. **Separation con Spacer**: Espacios verticales para mejorar la organización visual.
 *
 * Nota:
 * - Se conecta con SettingsVM para persistir cambios como el modo oscuro.
 */