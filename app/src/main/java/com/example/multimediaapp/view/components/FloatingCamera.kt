package com.example.multimediaapp.view.components

// Clase base para Activities en aplicaciones con Compose
import androidx.activity.ComponentActivity

// Conjunto de íconos predefinidos de Material
import androidx.compose.material.icons.Icons
// Ícono específico de "Agregar foto"
import androidx.compose.material.icons.filled.AddAPhoto
// Botón flotante de Material 3
import androidx.compose.material3.FloatingActionButton
// Composable para mostrar un ícono
import androidx.compose.material3.Icon
// Marca una función como componente de Jetpack Compose
import androidx.compose.runtime.Composable

/**
 * 🔹 Activity vacía llamada PicCatcher
 *
 * Extiende de ComponentActivity.
 * Actualmente no tiene implementación.
 *
 * Probablemente esté pensada para:
 * - Manejar permisos de cámara
 * - Lanzar un intent de captura de imagen
 * - Integrar CameraX
 */
class PicCatcher : ComponentActivity() {

    //TODO()
}

/**
 * 🔹 Botón flotante de cámara
 *
 * Muestra un FloatingActionButton con un ícono de cámara.
 * Ejecuta la función onClick cuando el usuario lo presiona.
 */

@Composable
fun FloatCamera(onClick: () -> Unit) {
    FloatingActionButton(
        // Función lambda que se ejecuta al hacer clic
        onClick = { onClick() },
    ) {
        // Acción al pulsar el botón
        Icon(
            // Ícono de cámara dentro del botón
            Icons.Filled.AddAPhoto,
            // Descripción para accesibilidad (TalkBack)
            "Sacar foto")
    }
}