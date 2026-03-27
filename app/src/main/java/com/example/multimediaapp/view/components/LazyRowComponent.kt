package com.example.multimediaapp.view.components

import android.content.Intent
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
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.multimediaapp.model.BandDTO

/**
 * ImagesRowList:
 *
 * Composable que muestra las imágenes de los álbumes de una banda en una fila horizontal.
 * Cada imagen es clicable y, si existe un enlace asociado, abre la URL en un navegador.
 *
 * @param band Objeto BandDTO que contiene:
 *             - albumImages: lista de URLs de las imágenes de los álbumes
 *             - albumLinks: lista de URLs de cada álbum para abrir en navegador
 * @param modifier Modifier opcional para personalizar tamaño, padding o estilo externo.
 *
 * Funcionamiento:
 * 1. LazyRow: lista horizontal optimizada que renderiza solo los elementos visibles.
 * 2. itemsIndexed: itera sobre la lista de imágenes con su índice.
 * 3. AsyncImage: carga imágenes desde URL usando Coil de manera asíncrona.
 * 4. clickable: permite abrir el enlace correspondiente al hacer clic sobre la imagen.
 * 5. ContentScale.Crop: ajusta la imagen para llenar el contenedor recortando lo que sobresalga.
 * 6. Modifiers height/width/padding: definen tamaño fijo de cada imagen y separación entre ellas.
 */
@Composable
fun ImagesRowList(
    band: BandDTO,
    modifier: Modifier = Modifier,
) {
    // Obtenemos el contexto para iniciar Intents
    val context = LocalContext.current

    Box {
        LazyRow(modifier = modifier) {
            // Itera sobre las imágenes de los álbumes
            itemsIndexed(band.albumImages) { index, albumImage ->
                AsyncImage(
                    model = albumImage, // URL de la imagen
                    contentDescription = "album image", // accesibilidad
                    modifier = Modifier
                        .height(120.dp) // altura fija de la imagen
                        .width(120.dp)  // ancho fijo de la imagen
                        .padding(end = 8.dp) // separación horizontal entre imágenes
                        .clickable {
                            // Si existe enlace correspondiente, abre navegador
                            if (index < band.albumLinks.size) {
                                val link = band.albumLinks[index]
                                val intent = Intent(Intent.ACTION_VIEW, link.toUri())
                                context.startActivity(intent)
                            }
                        },
                    contentScale = ContentScale.Crop // recorta para llenar el contenedor
                )
            }
        }
    }
}

/*
Notas adicionales:

1. LazyRow:
   - Lista horizontal optimizada para muchas imágenes.
   - Solo renderiza los elementos visibles para mejorar rendimiento.

2. itemsIndexed:
   - Permite acceder al índice de cada imagen para asociarla con el enlace correspondiente.

3. AsyncImage (Coil):
   - Carga imágenes de manera asíncrona.
   - Mejora el rendimiento y evita bloqueos de UI.

4. clickable + Intent:
   - Abre la URL del álbum en el navegador si existe.
   - Usa LocalContext.current para obtener el contexto de la actividad actual.

5. Modifiers:
   - height y width fijos aseguran uniformidad en la fila.
   - padding separa visualmente cada imagen.

6. ContentScale.Crop:
   - La imagen ocupa todo el espacio asignado y recorta las partes que no caben.
*/