package com.example.multimediaapp.view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.ui.theme.cardColumnModifier

@Composable
fun CardRowComponent(
    band: BandDTO
) {
    // Estado para controlar si el texto está expandido o no
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = cardColumnModifier
                    .animateContentSize(), // Animación suave al cambiar de tamaño
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { isExpanded = !isExpanded } // Permite expandir al tocar la tarjeta
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = band.description,
                        // Si no está expandido, corta el texto en la línea 3
                        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // "Botón" de Seguir leyendo / Ver menos
                    Text(
                        text = if (isExpanded) "Ver menos" else "Seguir leyendo...",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
