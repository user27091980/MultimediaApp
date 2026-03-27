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

/**
 * BandHeader:
 *
 * Composable que muestra la cabecera (banner) de una banda en una tarjeta visual.
 * Permite:
 * - Mostrar la imagen de banner de la banda desde una URL.
 * - Hacer clic en el banner para abrir un enlace externo (band.headerLink).
 * - Ajustar automáticamente tamaño y recorte de la imagen.
 *
 * @param band Objeto BandDTO que contiene:
 *  - banner: URL de la imagen del banner
 *  - name: nombre de la banda (para contentDescription)
 *  - headerLink: enlace externo que se abre al hacer clic
 */
@Composable
fun BandHeader(band: BandDTO) {

    val headerHeight = 100.dp
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth() // ocupa todo el ancho del contenedor padre
            .height(headerHeight) // altura del banner
            .padding(horizontal = 1.dp) // pequeño margen lateral
            .clip(RoundedCornerShape(8.dp)), // bordes redondeados
        contentAlignment = Alignment.Center
    ) {
        Image(
            // Carga de imagen asíncrona desde URL usando Coil
            painter = rememberAsyncImagePainter(band.banner),
            // Descripción de accesibilidad (lectores de pantalla)
            contentDescription = band.name,
            // Modificadores para tamaño y clic
            modifier = Modifier
                .height(headerHeight * 0.9f)
                .clickable {
                    // Abre enlace externo si existe
                    if (band.headerLink.isNotEmpty()) {
                        val intent = Intent(Intent.ACTION_VIEW, band.headerLink.toUri())
                        context.startActivity(intent)
                    }
                },
            // Escala la imagen para llenar el contenedor y recorta excedentes
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * ===Notas de implementación===
 *
 * 1. @Composable: Permite que esta función sea parte de la UI declarativa de Jetpack Compose.
 * 2. Box: Contenedor que permite centrar la imagen y aplicar clipping y padding.
 * 3. Modifier.fillMaxWidth() / height(): Define tamaño de la cabecera.
 * 4. Modifier.clip(): Aplica bordes redondeados a la imagen.
 * 5. rememberAsyncImagePainter: Optimiza carga de imágenes desde URL y evita recargas innecesarias.
 * 6. ContentScale.Crop: Recorta la imagen si no coincide con las proporciones del contenedor.
 * 7. contentDescription: Esencial para accesibilidad (usuarios con lectores de pantalla).
 * 8. click en la imagen: Permite abrir un navegador con un enlace externo usando Intent.ACTION_VIEW.
 *
 * Este Composable es ideal para mostrar de forma atractiva el banner de la banda
 * en pantallas como MainScreen o BandScreen.
 */