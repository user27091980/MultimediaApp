package com.example.multimediaapp.view.components

import android.content.Intent
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
import com.example.multimediaapp.model.BandDTO

// Componente Jetpack Compose que muestra la cabecera (banner) de una banda.
// Recibe un objeto BandUiState con la información de la banda.

/**
 * @param band recibe un objeto con la info de la banda incluyendo la url de la imagen del banner
 */
@Composable
fun BandHeader(band: BandDTO) {

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
            painter = rememberAsyncImagePainter(band.banner),
            // Descripción de la imagen para accesibilidad (lectores de pantalla).
            contentDescription = band.name,
            // Modificadores para definir el tamaño y comportamiento de la imagen.
            modifier = Modifier
                .height(headerHeight * 0.9f)
                .clickable {
                    if (band.headerLink.isNotEmpty()) {
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

/*
 * Este archivo define un componente de UI en Jetpack Compose que muestra
 * la cabecera (banner) de una banda.
 *
 * COMPONENTE PRINCIPAL:
 *
 * BandHeader(band: BandDTO)
 * - Recibe un objeto BandDTO con la información de la banda.
 * - Muestra la imagen del banner de la banda.
 *
 * ESTRUCTURA:
 *
 * Box:
 * - Contenedor principal que permite posicionar elementos.
 * - Se utiliza para aplicar tamaño, bordes y alineación.
 * - Incluye:
 *   - fillMaxWidth(): ocupa todo el ancho disponible
 *   - height(): define la altura del banner
 *   - padding(): márgenes laterales
 *   - clip(): recorta la imagen con bordes redondeados
 *
 * Image:
 * - Componente que muestra la imagen del banner.
 * - Usa rememberAsyncImagePainter para cargar imágenes desde URL.
 *
 * rememberAsyncImagePainter (Coil):
 * - Carga imágenes de forma asíncrona.
 * - Mejora el rendimiento evitando recargas innecesarias.
 * - Muy usado en apps modernas con imágenes remotas.
 *
 * contentScale = ContentScale.Crop:
 * - Ajusta la imagen al contenedor.
 * - Recorta partes de la imagen si no encaja exactamente.
 *
 * click en la imagen:
 * - La imagen es clickable.
 * - Si existe un headerLink:
 *   - Se abre un Intent ACTION_VIEW.
 *   - Se lanza el navegador con la URL asociada.
 *
 * LocalContext:
 * - Permite acceder al contexto de Android dentro de Compose.
 * - Se utiliza para lanzar intents.
 *
 * IMPORTANTE:
 *
 * - contentDescription:
 *   - Mejora la accesibilidad.
 *   - Permite que lectores de pantalla describan la imagen.
 *
 * - Uri.toUri():
 *   - Convierte un String en un objeto Uri para usarlo en intents.
 *
 * BENEFICIOS:
 *
 * - UI declarativa con Compose.
 * - Carga eficiente de imágenes con Coil.
 * - Interactividad (click en banner).
 * - Código limpio, reutilizable y fácil de mantener.
 */




