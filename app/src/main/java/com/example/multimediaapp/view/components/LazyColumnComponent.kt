package com.example.multimediaapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.viewmodel.uistate.BandUiState
import com.example.multimediaapp.viewmodel.uistate.MainUiState

/**
 * @author andrés
 * @param bands
 * @param onImageClick
 */
@Composable
fun CardList(
    bands: List<MainDTO>, onImageClick: (MainDTO) -> Unit // Lambda que recibe la banda clicada
) {
    // LazyColumn permite crear listas verticales, cargando solo los elementos visibles.
    // Es útil para listas grandes, para optimizar rendimiento.
    LazyColumn() {
        // Itera sobre la lista de URLs de imágenes de los álbumes.
        // 'imageBand' es cada URL de la lista.

        items(bands) { mainBand ->
            Image(
                // Cargamos la imagen desde la URL usando Coil y rememberAsyncImagePainter
                painter = rememberAsyncImagePainter(mainBand.imageBand),
                // Descripción para accesibilidad
                contentDescription = "album image",
                // Modificadores de la imagen
                modifier = Modifier
                    .height(120.dp)// Altura fija de cada imagen
                    .width(120.dp)// Ancho fijo de cada imagen
                    .padding(end = 8.dp)
                    .clickable { onImageClick(mainBand) },
                // Escala de la imagen para que llene su contenedor y recorte lo sobrante
                contentScale = ContentScale.Crop,
            )
        }
    }
}

val previewBands = listOf(
    MainDTO("Metallica", "https://via.placeholder.com/300x150.png?text=Metallica"),
    MainDTO("Iron Maiden", "https://via.placeholder.com/300x150.png?text=Iron+Maiden"),
    MainDTO("AC/DC", "https://via.placeholder.com/300x150.png?text=ACDC")
)

@Preview
@Composable
fun CardListPreview() {
    CardList(
        bands = previewBands,
        onImageClick = {}
    )
}