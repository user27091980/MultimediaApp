package com.example.multimediaapp.view.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.model.BandDTO

@Composable
fun BandHeader(band: BandDTO) {
    val headerHeight = 110.dp // Altura aumentada para dar más cuerpo
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth() // Asegura que use el 100% del ancho disponible
            .height(headerHeight)
            .padding(horizontal = 0.dp) // Eliminamos padding para que pegue a los bordes
            .clip(RoundedCornerShape(0.dp)) // Quitamos redondeo para un look "Full Width"
            .clickable {
                if (band.headerLink.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, band.headerLink.toUri())
                    context.startActivity(intent)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Imagen de fondo (Banner)
        Image(
            painter = rememberAsyncImagePainter(band.banner),
            contentDescription = band.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Llena todo el ancho recortando lo necesario
        )

        // Icono de enlace
        if (band.headerLink.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp) // Más margen para que no esté pegado al borde físico
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Link,
                    contentDescription = "Abrir enlace",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp) // Icono un poco más grande acorde al banner
                )
            }
        }
    }
}
