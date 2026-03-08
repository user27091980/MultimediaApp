package com.example.multimediaapp.view.components

// Importaciones necesarias de Compose
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

    Column(
        modifier = Modifier.padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        // Un solo FlowRow para todos los chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Chips simples
            TagItem(band.components)
            TagItem(band.recordLabel)
            TagItem(band.style)

        }
    }
}


/**
 * Componente reutilizable que representa una etiqueta individual.
 * Se usa para mostrar texto con fondo redondeado tipo "chip".
 */

@Composable
fun TagItem(tag: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.LightGray,
        tonalElevation = 2.dp
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color.Black
        )
    }
}
