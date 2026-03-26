package com.example.multimediaapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.ui.theme.cardColumnModifier

/**
 * Componente de UI para mostrar información de la banda en una tarjeta (Card).
 * Esta columna puede contener múltiples cards si se quisiera expandir.
 *
 * @param band Objeto BandUiState con toda la información de la banda
 */
@Composable

fun CardRowComponent(
    band: BandDTO
) {
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Card(
                modifier = cardColumnModifier,
                shape = RoundedCornerShape(10),//no más de 50
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = band.description,
                    modifier = Modifier.padding(16.dp)
                )
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

