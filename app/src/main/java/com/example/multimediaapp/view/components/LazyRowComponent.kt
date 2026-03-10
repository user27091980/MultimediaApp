package com.example.multimediaapp.view.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.core.net.toUri
import com.example.multimediaapp.model.BandDTO

/* Componente Jetpack Compose que muestra las imágenes de los álbumes de una banda en una fila horizontal.
*Recibe:
 - 'band': el objeto BandUiState con los datos de la banda, incluyendo la lista de imágenes de álbumes.
 - 'modifier': un Modifier opcional para personalizar el estilo desde fuera del componente.

 */
/**
 * @author andrés
 * @param band
 * @param modifier
 *
 */
@Composable
fun ImagesRowList(
    band: BandDTO,
    modifier: Modifier = Modifier,
) {
    // LazyRow permite crear listas horizontales, cargando solo los elementos visibles.
    // Útil para listas grandes, para optimizar rendimiento.
    val context = LocalContext.current
    Box {
        LazyRow(modifier = modifier) {
            // Itera sobre la lista de URLs de imágenes de los álbumes.
            // 'albumImage' es cada URL de la lista.
            itemsIndexed(band.albumImages) { index, albumImage ->

                Image(
                    // Cargamos la imagen desde la URL usando Coil y rememberAsyncImagePainter
                    painter = rememberAsyncImagePainter(albumImage),
                    // Descripción para accesibilidad
                    contentDescription = "album image",
                    // Modificadores de la imagen
                    modifier = Modifier
                        .height(120.dp)// Altura fija de cada imagen
                        .width(120.dp)// Ancho fijo de cada imagen
                        .padding(end = 8.dp)
                        .clickable {
                            if (index < band.albumLinks.size) {
                                val link = band.albumLinks[index]
                                val intent = Intent(Intent.ACTION_VIEW, link.toUri())
                                context.startActivity(intent)
                            }

                        },// Espacio entre imágenes horizontales
                    // Escala de la imagen para que llene su contenedor y recorte lo sobrante
                    contentScale = ContentScale.Crop,

                    )
            }
        }
    }
}



