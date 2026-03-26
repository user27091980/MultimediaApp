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
import com.example.multimediaapp.R
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import com.example.multimediaapp.viewmodel.vm.SettingsVM
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Pantalla de configuración de la app.
 * Permite cambiar preferencias como el modo oscuro y mostrar información básica de la app.
 */
@Composable
fun SettingsScreen(vm: SettingsVM) {
    // Estado local para el switch de modo oscuro
    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Configuración",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Apariencia",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        SettingSwitch(
            title = "Modo oscuro",
            checked = uiState.darkMode,
            onCheckedChange = { vm.onDarkModeChange(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.version),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

