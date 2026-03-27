package com.example.multimediaapp.view.components

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * PicCatcher:
 *
 * Clase vacía que hereda de ComponentActivity.
 * Se puede utilizar como placeholder para futuras funcionalidades relacionadas con
 * la captura de imágenes o la integración con la cámara.
 *
 * Actualmente no contiene lógica, pero se puede extender para:
 * - Abrir cámara
 * - Tomar fotos
 * - Guardar imágenes en almacenamiento interno o remoto
 */
class PicCatcher : ComponentActivity() {
    // TODO: implementar lógica de cámara
}

/**
 * FloatCamera:
 *
 * Composable que muestra un FloatingActionButton (FAB) con un icono de cámara.
 * Se puede colocar en cualquier pantalla para permitir al usuario tomar fotos.
 *
 * @param modifier Modificador opcional para ajustar tamaño, posición, padding u otras propiedades de Compose.
 * @param onClick Callback que se ejecuta al pulsar el botón.
 *
 * ===Detalles de implementación===
 * - FloatingActionButton: botón flotante que sigue el Material Design.
 * - Icon: usa Icons.Filled.AddAPhoto para representar la acción de capturar foto.
 * - onClick: permite ejecutar cualquier acción (abrir cámara, abrir galería, etc.).
 */
@Composable
fun FloatCamera(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() }, // Llama al callback proporcionado
        modifier = modifier // Aplica modificaciones externas
    ) {
        Icon(
            imageVector = Icons.Filled.AddAPhoto,
            contentDescription = "Sacar foto" // Para accesibilidad
        )
    }
}

/**
 * ===Notas adicionales===
 * - PicCatcher sirve como clase base o actividad de cámara futura.
 * - FloatCamera es reutilizable y flexible; se puede colocar en cualquier parte de la UI.
 * - contentDescription en Icon es importante para accesibilidad (lectores de pantalla).
 * - modifier permite integrar animaciones, padding, tamaño y ubicación según el diseño de la pantalla.
 */