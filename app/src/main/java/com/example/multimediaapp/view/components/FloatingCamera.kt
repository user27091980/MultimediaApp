package com.example.multimediaapp.view.components

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Clase vacía heredada de ComponentActivity.
 *
 * Se puede usar como placeholder para futuras funcionalidades relacionadas
 * con captura de imágenes o integración con cámara.
 */
class PicCatcher : ComponentActivity() {

    //TODO()
}

/**
 * Composable que muestra un FloatingActionButton para tomar fotos.
 *
 * @param modifier Modificador opcional para ajustar tamaño, posición, padding, etc.
 * @param onClick Callback que se ejecuta al pulsar el botón.
 */
@Composable
fun FloatCamera(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }, // Llama al callback pasado
        modifier = modifier// Aplica modificaciones externas
    ) {
        // Icono de cámara dentro del botón flotante
        Icon(Icons.Filled.AddAPhoto, "Sacar foto")
    }
}


/*
 * Este archivo define componentes relacionados con la funcionalidad de cámara
 * y captura de imágenes dentro de la aplicación.
 *
 * CLASE PRINCIPAL:
 *
 * PicCatcher : ComponentActivity
 * - Es una actividad vacía que actualmente actúa como placeholder.
 * - Se puede utilizar en el futuro para integrar funcionalidades de cámara
 *   o manejo avanzado de imágenes.
 * - Hereda de ComponentActivity, lo que permite usar Jetpack Compose si se necesita.
 *
 * COMPONENTE COMPOSABLE:
 *
 * FloatCamera(modifier: Modifier = Modifier, onClick: () -> Unit)
 *
 * - Representa un FloatingActionButton (FAB) con icono de cámara.
 * - Se utiliza comúnmente para acciones principales como tomar fotos.
 *
 * FloatingActionButton:
 * - Botón flotante típico de Material Design.
 * - Se posiciona sobre la UI para acciones importantes.
 *
 * Icon:
 * - Muestra el icono AddAPhoto, representando la acción de capturar imagen.
 * - Incluye una descripción ("Sacar foto") para accesibilidad (TalkBack).
 *
 * PARÁMETROS:
 *
 * modifier:
 * - Permite personalizar el comportamiento visual del botón.
 * - Ejemplo: posición, tamaño, padding, animaciones, etc.
 *
 * onClick:
 * - Callback que se ejecuta cuando el usuario presiona el botón.
 * - Normalmente se utiliza para abrir la cámara o lanzar un intent de captura.
 *
 * CONCEPTOS IMPORTANTES:
 *
 * ComponentActivity:
 * - Clase base para actividades modernas en Android con soporte para Compose.
 *
 * Composable:
 * - Función que define elementos de UI en Jetpack Compose.
 *
 * FloatingActionButton:
 * - Botón de acción principal en interfaces modernas.
 *
 * USO:
 * - Este componente se puede integrar en pantallas donde el usuario necesite:
 *   - Tomar fotos
 *   - Subir imágenes
 *   - Capturar contenido multimedia
 *
 * BENEFICIOS:
 * - Código reutilizable
 * - Separación de UI y lógica
 * - Fácil integración en distintas pantallas
 * - Diseño consistente con Material Design
 */