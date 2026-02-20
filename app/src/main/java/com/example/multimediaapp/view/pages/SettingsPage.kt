package com.example.multimediaapp.view.pages

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.viewmodel.vm.SettingsVM

/**
 * Pantalla de configuración de la app.
 * Permite cambiar preferencias como el modo oscuro y mostrar información básica de la app.
 */
@Composable
fun SettingsScreen( vm: SettingsVM = viewModel()) {
    // Estado local para el switch de modo oscuro
    var darkMode by remember { mutableStateOf(false) }
// Columna principal que organiza todos los elementos de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()// Ocupa todo el espacio disponible
            .padding(16.dp)// Margen interno de 16dp en todos los lados
    ) {
        // Título de la pantalla
        Text(
            text = "configuración",
            style = MaterialTheme.typography.headlineMedium// Estilo de título principal de Material3
        )

        Spacer(modifier = Modifier.height(24.dp))// Separación vertical de 24dp

        // Sección de apariencia
        Text("Apariencia", style = MaterialTheme.typography.titleMedium)// Subtítulo
// Switch para el modo oscuro
        SettingSwitch(
            title = "Modo óscuro",// Texto del switch
            checked = darkMode,// Estado del switch
            onCheckedChange = { darkMode = it },
        )
        Spacer(modifier = Modifier.height(24.dp))// Separación vertical de 24dp

        // Información sobre la versión de la app
        Text("App Version: 1.0", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Agradecimientos: 10% a chatGpt,\n 70% a Pedro que le debo unos porteos" +
                "\ny 5% a mí mismo por no desquiciarme",
            style = MaterialTheme.typography.titleMedium)

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
            checked = checked,// Estado actual
            onCheckedChange = onCheckedChange// Callback al cambiar
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