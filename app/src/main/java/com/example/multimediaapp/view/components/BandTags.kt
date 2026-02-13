package com.example.multimediaapp.view.components

// Importaciones necesarias de Compose
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.viewmodel.uistate.BandUiState

/**
 * Componente que muestra las etiquetas (tags) de una banda.
 * Incluye información como:
 * - Componentes del grupo
 * - Discográfica
 * - Estilo musical
 * - Discografía
 */
@Composable
fun BandTags(band: BandUiState) {
// Columna principal que organiza las filas de etiquetas verticalmente
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp) // separación vertical entre filas
    ) {
// Fila para los componentes de la banda
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            TagItem(band.components)
        }
        // Fila para discográfica
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { TagItem(band.recordLabel) }
        // Fila para el estilo musical
        FlowRow {
            TagItem(band.style)
        }
        // Recorre la lista de discos y crea una etiqueta por cada álbum
        FlowColumn {
            band.discography.forEach { album ->
                TagItem(album)
            }
        }
    }
}

/**
 * Componente reutilizable que representa una etiqueta individual.
 * Se usa para mostrar texto con fondo redondeado tipo "chip".
 */

@Composable
fun TagItem(tag: String) {
    Text(
        text = tag,// Texto de la etiqueta
        modifier = Modifier
            // Fondo gris claro con esquinas redondeadas
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            // Espaciado interno del texto
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = Color.Black// Color del texto
    )
}
