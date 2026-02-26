package com.example.multimediaapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.viewmodel.uistate.BandUiState

// Componente Jetpack Compose que muestra la cabecera (banner) de una banda.
// Recibe un objeto BandUiState con la información de la banda.

@Composable
fun BandHeader(band: BandUiState) {

    Image(
        // 'painter' define la fuente de la imagen.
        // `rememberAsyncImagePainter` de Coil permite cargar imágenes desde URLs de forma asíncrona.
        painter = rememberAsyncImagePainter(band.headerImage),
        // Descripción de la imagen para accesibilidad (lectores de pantalla).
        contentDescription = "Banner de ${band.name}",
        // Modificadores para definir el tamaño y comportamiento de la imagen.
        modifier = Modifier
            .fillMaxWidth()// Ocupa todo el ancho disponible
            .height(100.dp),// Altura fija de 100dp
        // Escala de la imagen dentro del espacio definido:
        // 'Crop' recorta la imagen si no coincide exactamente con el tamaño del contenedor.
        contentScale = ContentScale.Crop
    )
}

/**
 * 🔹 Comentarios clave
 *
 * @Composable: Permite usar esta función dentro de otros componentes de Compose y renderizar UI declarativa.
 *
 * rememberAsyncImagePainter: Carga imágenes desde URLs y recuerda el resultado para optimizar rendimiento y evitar recargas innecesarias.
 *
 * Modifier.fillMaxWidth(): Hace que la imagen se extienda a lo ancho de la pantalla o contenedor padre.
 *
 * Modifier.height(200.dp): Define la altura del banner de la banda.
 *
 * ContentScale.Crop: Ajusta la imagen para llenar el espacio disponible, recortando lo que sobresalga si es necesario.
 *
 * contentDescription: Muy importante para accesibilidad, describe la imagen para lectores de pantalla.
 */





