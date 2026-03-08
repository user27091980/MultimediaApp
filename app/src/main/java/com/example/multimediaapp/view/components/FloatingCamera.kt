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
PicCatcher: placeholder para futuras actividades relacionadas con la cámara. Actualmente está vacío.

FloatCamera: Composable que crea un FloatingActionButton con un icono de cámara.

modifier_ permite ajustar posición, tamaño o animaciones externas.

onClick: callback para abrir la cámara o ejecutar cualquier acción al presionar el botón.

Icon: usa un icono del paquete Material Icons (AddAPhoto) y proporciona descripción para accesibilidad.
 */