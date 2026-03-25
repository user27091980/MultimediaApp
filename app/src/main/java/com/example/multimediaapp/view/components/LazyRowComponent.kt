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
                AsyncImage(
                    // Cargamos la imagen desde la URL usando Coil y rememberAsyncImagePainter
                    model = albumImage,
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
                    contentScale = ContentScale.Crop

                    )
            }
        }
    }
}
/*
 * Este archivo define un componente de UI en Jetpack Compose que muestra
 * una lista horizontal de imágenes de álbumes de una banda.
 *
 * COMPONENTE PRINCIPAL:
 *
 * ImagesRowList(band: BandDTO, modifier: Modifier = Modifier)
 *
 * - Recibe un objeto BandDTO con la información de la banda.
 * - Utiliza la lista albumImages para mostrar imágenes.
 * - Utiliza albumLinks para abrir enlaces asociados a cada imagen.
 *
 * ESTRUCTURA:
 *
 * LocalContext:
 * - Permite acceder al contexto de Android.
 * - Se usa para lanzar intents (abrir enlaces en navegador).
 *
 * Box:
 * - Contenedor que permite organizar elementos dentro.
 *
 * LazyRow:
 * - Lista horizontal optimizada.
 * - Solo renderiza los elementos visibles.
 * - Ideal para listas de imágenes desplazables horizontalmente.
 *
 * itemsIndexed:
 * - Permite iterar sobre la lista con acceso al índice.
 * - Se usa para vincular cada imagen con su enlace correspondiente.
 *
 * COMPONENTE DE IMAGEN:
 *
 * AsyncImage (Coil):
 * - Carga imágenes desde URL de forma asíncrona.
 * - Mejora rendimiento y gestión de memoria.
 *
 * - model: URL de la imagen (albumImage)
 * - contentDescription: descripción para accesibilidad
 *
 * MODIFICADORES:
 *
 * - height(120.dp) / width(120.dp):
 *   Define tamaño fijo para cada imagen.
 *
 * - padding(end = 8.dp):
 *   Añade separación entre imágenes.
 *
 * - clickable:
 *   Permite interacción del usuario.
 *   Al hacer clic:
 *   - Se obtiene el enlace correspondiente (albumLinks[index]).
 *   - Se lanza un Intent ACTION_VIEW para abrir el navegador.
 *
 * - contentScale = ContentScale.Crop:
 *   Ajusta la imagen al contenedor recortando el exceso.
 *
 * LÓGICA IMPORTANTE:
 *
 * if (index < band.albumLinks.size):
 * - Evita errores de índice fuera de rango.
 * - Garantiza que cada imagen tenga un enlace válido.
 *
 * CONCEPTOS CLAVE:
 *
 * LazyRow:
 * - Lista horizontal eficiente para grandes cantidades de datos.
 *
 * AsyncImage:
 * - Carga de imágenes optimizada usando Coil.
 *
 * Intent.ACTION_VIEW:
 * - Permite abrir enlaces externos en el navegador.
 *
 * toUri():
 * - Convierte un String en Uri para usarlo en intents.
 *
 * BENEFICIOS:
 *
 * - UI dinámica y desplazable horizontalmente.
 * - Integración con enlaces externos.
 * - Alto rendimiento gracias a LazyRow.
 * - Código reutilizable y modular.
 */



