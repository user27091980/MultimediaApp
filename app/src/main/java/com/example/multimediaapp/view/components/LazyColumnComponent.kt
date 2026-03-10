package com.example.multimediaapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.multimediaapp.model.MainDTO

/**
 * Composable que muestra una lista vertical de tarjetas con imagen y nombre de banda.
 *
 * @param bands Lista de objetos MainDTO que contienen la información de cada banda.
 * @param onImageClick Lambda que se ejecuta al hacer clic en la imagen de una banda.
 */
@Composable
fun CardList(
    bands: List<MainDTO>,
    onImageClick: (MainDTO) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bands) { mainBand ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // SubcomposeAsyncImage carga imágenes remotas de manera segura
                SubcomposeAsyncImage(
                    model = mainBand.imageBand,
                    contentDescription = mainBand.bandName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable { onImageClick(mainBand) },
                    contentScale = ContentScale.Crop
                ) {
                    val state = painter.state
                    if (state is coil.compose.AsyncImagePainter.State.Loading ||
                        state is coil.compose.AsyncImagePainter.State.Error
                    ) {
                        // Placeholder gris mientras carga o falla
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray)
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }

                Text(
                    text = mainBand.bandName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

// Datos de ejemplo para preview (Preview no carga imágenes remotas)
val previewBands = listOf(
    MainDTO("1", "Metallica", ""),
    MainDTO("2", "Iron Maiden", ""),
    MainDTO("3", "AC/DC", "")
)

@Composable
fun CardListPreview() {
    CardList(
        bands = previewBands,
        onImageClick = {}
    )
}
