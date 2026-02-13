package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimediaapp.ui.theme.cardColumnModifier
import com.example.multimediaapp.viewmodel.uistate.BandUiState

/**
 * Componente de UI para mostrar información de la banda en una tarjeta (Card).
 * Esta columna puede contener múltiples cards si se quisiera expandir.
 *
 * @param band Objeto BandUiState con toda la información de la banda
 */
@Composable
fun CardRowComponent(
    band: BandUiState
) {

    //Columna que organiza las cards verticalmente
    Box {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()// Ocupa todo el ancho disponible
        ) {
            // Tarjeta principal con la información de la banda
            Card(
                cardColumnModifier// Modifier personalizado desde el tema, puede incluir padding, shape, elevation
            ) {
// Texto dentro de la tarjeta que muestra la descripción de la banda
                Text(text = band.textInfo)

            }
        }
    }
}

