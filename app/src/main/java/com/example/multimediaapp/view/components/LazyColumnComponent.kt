package com.example.multimediaapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.model.MainDTO

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
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Itera sobre la lista de URLs de imágenes de los álbumes.
        // 'imageBand' es cada URL de la lista.

        items(bands) { mainBand ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente todo dentro de la columna
            ) {
                Image(
                    // Cargamos la imagen desde la URL usando Coil y rememberAsyncImagePainter
                    painter = rememberAsyncImagePainter(mainBand.imageBand),
                    // Descripción para accesibilidad
                    contentDescription = "album image",
                    // Modificadores de la imagen
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable { onImageClick(mainBand) },
                    // Escala de la imagen para que llene su contenedor y recorte lo sobrante
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = mainBand.bandName,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

val previewBands = listOf(
    MainDTO("1","Metallica", "https://via.placeholder.com/300x150.png?text=Metallica"),
    MainDTO("2","Iron Maiden", "https://via.placeholder.com/300x150.png?text=Iron+Maiden"),
    MainDTO("3","AC/DC", "https://via.placeholder.com/300x150.png?text=ACDC")
)

@Preview
@Composable
fun CardListPreview() {
    CardList(
        bands = previewBands,
        onImageClick = {}
    )
}