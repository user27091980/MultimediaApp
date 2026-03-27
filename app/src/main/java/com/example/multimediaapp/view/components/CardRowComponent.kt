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
 * CardRowComponent:
 *
 * Composable que muestra la información de una banda dentro de una tarjeta (Card).
 * Es útil para mostrar descripciones, estilo, discográfica, o cualquier detalle de la banda.
 *
 * ===Estructura===
 * - Box: contenedor principal que permite fondo y alineación de elementos.
 * - FlowRow: organiza las tarjetas horizontalmente y permite que se ajusten automáticamente
 *   si hay más de una.
 * - Card: componente Material que contiene la información de la banda.
 * - Text: muestra el contenido dentro de la Card (por ejemplo, la descripción).
 *
 * ===Parámetros===
 * @param band Objeto BandDTO que contiene toda la información relevante de la banda
 *             (nombre, descripción, estilo, componentes, etc.).
 *
 * ===Propiedades de UI===
 * - `cardColumnModifier`: Modifier personalizado que puede incluir padding, tamaño, elevación u otras propiedades.
 * - `RoundedCornerShape(10)`: esquinas redondeadas de la tarjeta (no más de 50dp).
 * - `MaterialTheme.colorScheme.primary`: fondo de la tarjeta adaptado al tema.
 * - `MaterialTheme.colorScheme.background`: fondo del contenedor Box.
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
                shape = RoundedCornerShape(10),
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

/**
 * ===Notas===
 * - Box permite superponer elementos y establecer un fondo consistente.
 * - FlowRow organiza tarjetas horizontalmente, ajustándose si hay varias.
 * - Card aplica estilo Material y eleva visualmente la información.
 * - cardColumnModifier se puede reutilizar y ajustar para consistencia visual en toda la app.
 * - Text dentro de la Card muestra la información principal de la banda.
 */