package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable base para todos los botones de la aplicación.
 *
 * Permite reutilizar un estilo consistente:
 * - Ancho completo por defecto
 * - Bordes redondeados
 * - Colores del tema
 * - Habilitado / deshabilitado
 */
@Composable
fun BaseButton(
    text: String,   // Texto que se mostrará en el botón
    onClick: () -> Unit,// Acción al pulsar el botón
    modifier: Modifier = Modifier, // Modifier opcional para composición externa
    enabled: Boolean = true // Modifier opcional para composición externa
) {

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),// Siempre ocupa todo el ancho disponible
        shape = RoundedCornerShape(20.dp),// Bordes redondeados de 20dp
        enabled = enabled,// Estado habilitado/deshabilitado
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Fondo del botón
            contentColor = MaterialTheme.colorScheme.onPrimary// Color del texto
        )
    ) {
        Text(
            text = text,

            )
    }
}


/**
 * Botón específico de Login
 *
 * Reutiliza BaseButton con texto predefinido "Login"
 */
@Composable
fun ButtonLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * Botón específico para Aceptar
     *
     * Reutiliza BaseButton con texto predefinido "Aceptar"
     */
    BaseButton(
        text = "Login",
        onClick = onClick,
        modifier = modifier
    )
}
/**
 * Botón específico para Cancelar
 *
 * Reutiliza BaseButton con texto predefinido "Cancelar"
 */
@Composable
fun ButtonAcept(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Aceptar",
        onClick = onClick,
        modifier = modifier
    )
}
/**
 * Botón específico para Registrar
 *
 * Reutiliza BaseButton con texto predefinido "Registrar"
 */
@Composable
fun ButtonCancel(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Cancelar",
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun ButtonRegister(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Registrar",
        onClick = onClick,
        modifier = modifier
    )
}

/*
BaseButton: botón genérico reutilizable con estilo consistente (colores, bordes, ancho completo).

Botones específicos (ButtonLogin, ButtonAcept, etc.) → reutilizan BaseButton con texto fijo,
 lo que evita repetir código y mantiene coherencia visual.

modifier  permite añadir padding, peso o cualquier otra modificación externa en Compose.

enabled se puede desactivar el botón si es necesario (por ejemplo, mientras se hace login).
 */