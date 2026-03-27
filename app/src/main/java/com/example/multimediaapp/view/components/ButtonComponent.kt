package com.example.multimediaapp.view.components

import android.R.attr.description
import android.R.attr.name
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.R
import com.example.multimediaapp.model.BandDTO
import java.util.UUID

/**
 * BaseButton:
 *
 * Composable genérico para crear botones con estilo consistente en toda la app.
 *
 * ===Características===
 * - Ocupa todo el ancho disponible por defecto (`fillMaxWidth`)
 * - Bordes redondeados (`RoundedCornerShape(20.dp)`)
 * - Colores del tema Material3:
 *   - Fondo → `primary`
 *   - Texto → `onPrimary`
 * - Soporta estado habilitado/deshabilitado (`enabled`)
 * - Permite personalización externa mediante `modifier`
 *
 * ===Parámetros===
 * @param text Texto a mostrar en el botón
 * @param onClick Acción que se ejecuta al pulsar el botón
 * @param modifier Modifier opcional para ajustar tamaño, padding u otras propiedades
 * @param enabled Boolean que indica si el botón está habilitado (true) o deshabilitado (false)
 *
 * ===Uso típico===
 * ```
 * BaseButton(
 *     text = "Aceptar",
 *     onClick = { /* acción */ },
 *     modifier = Modifier.padding(16.dp)
 * )
 * ```
 */
@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = text)
    }
}

/**
 * Botones específicos reutilizando BaseButton
 *
 * Cada función define un botón con texto fijo para casos comunes:
 * - ButtonLogin → "Login"
 * - ButtonAccept → "Aceptar"
 * - ButtonCancel → "Cancelar"
 * - ButtonRegister → "Registrar"
 *
 * Ventajas:
 * 1. Evita duplicar código de estilos
 * 2. Mantiene coherencia visual en la app
 * 3. Permite modificar el comportamiento del botón sin tocar los estilos
 */
@Composable
fun ButtonLogin(onClick: () -> Unit, modifier: Modifier = Modifier) {
    BaseButton(text = stringResource(R.string.login), onClick = onClick, modifier = modifier)
}

@Composable
fun ButtonAccept(onClick: () -> Unit, modifier: Modifier = Modifier) {
    BaseButton(text = stringResource(R.string.aceptar), onClick = onClick, modifier = modifier)
}

@Composable
fun ButtonCancel(onClick: () -> Unit, modifier: Modifier = Modifier) {
    BaseButton(text = stringResource(R.string.cancelar), onClick = onClick, modifier = modifier)
}

@Composable
fun ButtonRegister(onClick: () -> Unit, modifier: Modifier = Modifier) {
    BaseButton(text = stringResource(R.string.registro), onClick = onClick, modifier = modifier)
}

/**
 * ===Resumen===
 * - BaseButton: botón genérico con estilo uniforme.
 * - Botones específicos: reutilizan BaseButton con texto fijo para acciones frecuentes.
 * - `modifier` permite ajustes externos sin romper consistencia visual.
 * - `enabled` permite desactivar botones en casos como loading o validaciones de formulario.
 */