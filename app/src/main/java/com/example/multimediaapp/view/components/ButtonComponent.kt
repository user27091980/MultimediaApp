package com.example.multimediaapp.view.components

// Modificador para ocupar todo el ancho disponible
import androidx.compose.foundation.layout.fillMaxWidth
// Forma con esquinas redondeadas
import androidx.compose.foundation.shape.RoundedCornerShape
// Botón de Material 3 con estilo tonal
import androidx.compose.material3.FilledTonalButton
// Composable para mostrar texto
import androidx.compose.material3.Text
// Marca una función como componente de Compose
import androidx.compose.runtime.Composable
// Permite modificar tamaño, padding, etc.
import androidx.compose.ui.Modifier
// Unidad de medida en Compose (density-independent pixels)
import androidx.compose.ui.unit.dp
// Estilo personalizado de texto definido en el theme
import com.example.multimediaapp.ui.theme.styleButtonText


/**
 * 🔹 Botón base reutilizable
 *
 * Este composable centraliza el diseño común de todos los botones
 * para evitar repetir código.
 */
@Composable
fun BaseButton(
    // Texto que mostrará el botón
    text: String,
    // Acción que se ejecuta cuando se hace clic
    onClick: () -> Unit,
    // Permite personalizar el Modifier desde fuera (opcional)
    modifier: Modifier = Modifier,
    // Indica si el botón está habilitado o deshabilitado
    enabled: Boolean = true
) {
    FilledTonalButton(
        // Acción al hacer clic
        onClick = onClick,
        // Ocupa todo el ancho disponible
        modifier = modifier.fillMaxWidth(),
        // Bordes redondeados de 20dp
        shape = RoundedCornerShape(20.dp),
        // Controla si el botón está activo
        enabled = enabled
    ) {
        // Contenido interno del botón (Texto)
        Text(
            text = text,
            // Aplica el estilo definido en el theme
            style = styleButtonText
        )
    }
}
/**
 * 🔹 Botón específico para Login
 * Reutiliza BaseButton cambiando únicamente el texto.
 */
@Composable
fun ButtonLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Login",
        onClick = onClick,
        modifier = modifier
    )
}
/**
 * 🔹 Botón de Aceptar
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
 * 🔹 Botón de Cancelar
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
/**
 * 🔹 Botón de Registro
 */

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