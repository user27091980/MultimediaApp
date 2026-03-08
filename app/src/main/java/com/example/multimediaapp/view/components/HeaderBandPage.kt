package com.example.multimediaapp.view.components

import android.content.Intent
import android.system.Os.link
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.viewmodel.uistate.BandUiState

// Componente Jetpack Compose que muestra la cabecera (banner) de una banda.
// Recibe un objeto BandUiState con la información de la banda.

/**
 * @param band recibe un objeto con la info de la banda incluyendo la url de la imagen del banner
 */
@Composable
fun BandHeader(band: BandUiState) {

    val headerHeight = 100.dp
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) // altura más pequeña
            .padding(horizontal = 1.dp) // margenes laterales
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            // 'painter' define la fuente de la imagen.
            // `rememberAsyncImagePainter` de Coil permite cargar imágenes desde URLs de forma asíncrona.
            painter = rememberAsyncImagePainter(band.headerImage),
            // Descripción de la imagen para accesibilidad (lectores de pantalla).
            contentDescription = band.name,
            // Modificadores para definir el tamaño y comportamiento de la imagen.
            modifier = Modifier.height(headerHeight*0.9f)
                .clickable{
                    if(band.headerLink.isNotEmpty()){

                        val intent = Intent(Intent.ACTION_VIEW, band.headerLink.toUri())
                        context.startActivity(intent)
                    }
                },
            // Escala de la imagen dentro del espacio definido:
            // 'Crop' recorta la imagen si no coincide exactamente con el tamaño del contenedor.
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * Teoría.
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





