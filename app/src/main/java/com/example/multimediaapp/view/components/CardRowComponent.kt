package com.example.multimediaapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
        // FlowRow organiza los elementos horizontalmente y envuelve automáticamente si no caben
        FlowRow(
            modifier = Modifier
                .fillMaxWidth() //ocupa todo el ancho disponible
                .fillMaxHeight()// Ocupa toda la altura disponible
        ) {
            // Tarjeta principal con la información de la banda
            Card(
                cardColumnModifier.background(MaterialTheme.colorScheme.background)// Modifier personalizado desde el tema, puede incluir padding, shape, elevation
            ) {
// Texto dentro de la tarjeta que muestra la descripción de la banda
                Text(text = band.textInfo) // Extrae el texto descriptivo de BandUiState

            }
        }
    }
}

/*
Box: contenedor principal que permite superponer o alinear elementos fácilmente.

FlowRow: organiza múltiples tarjetas horizontalmente y permite wrapping si hay muchas.

Card: tarjeta material que contiene la información de la banda.

cardColumnModifier: Modifier personalizado, puede incluir padding, elevación, shape u otras propiedades de UI.

MaterialTheme.colorScheme.background: el fondo de la tarjeta se adapta al tema de la app.

Text muestra la descripción o información de la banda.
 */

