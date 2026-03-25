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

/*
 * Este archivo define un componente de interfaz de usuario en Jetpack Compose
 * que muestra información de una banda dentro de una tarjeta (Card).
 *
 * COMPONENTE PRINCIPAL:
 *
 * CardRowComponent(band: BandDTO)
 * - Recibe un objeto BandDTO con los datos de la banda.
 * - Su función es mostrar información relevante, en este caso la descripción.
 *
 * ESTRUCTURA DE LA UI:
 *
 * Box:
 * - Contenedor principal.
 * - Permite posicionar y organizar elementos dentro de él.
 * - Se utiliza aquí para aplicar el color de fondo de la pantalla.
 *
 * FlowRow:
 * - Organiza los elementos horizontalmente.
 * - Si no caben en una línea, se envuelven automáticamente a la siguiente.
 * - Es útil cuando se quieren mostrar múltiples tarjetas.
 *
 * Card:
 * - Componente de Material Design que representa una tarjeta.
 * - Se utiliza para mostrar contenido de forma visualmente destacada.
 * - Incluye:
 *   - Forma redondeada (RoundedCornerShape)
 *   - Color de fondo definido por el tema
 *
 * Text:
 * - Muestra la descripción de la banda.
 * - Se le aplica padding para mejorar la legibilidad.
 *
 * ESTILO:
 *
 * - Se usa MaterialTheme para mantener coherencia visual con la app.
 * - El color de la tarjeta proviene del esquema de colores del tema.
 * - Se utiliza un modifier personalizado (cardColumnModifier) para aplicar
 *   estilos adicionales como padding o tamaño.
 *
 * CONCEPTOS IMPORTANTES:
 *
 * Modifier:
 * - Permite modificar la apariencia y comportamiento de los componentes.
 *
 * cardColumnModifier:
 * - Es un modifier personalizado definido en otro archivo.
 * - Se usa para mantener consistencia en el diseño de las tarjetas.
 *
 * FlowRow:
 * - Ideal para diseños dinámicos y adaptables a diferentes pantallas.
 *
 * BENEFICIOS:
 * - Código modular y reutilizable.
 * - Diseño adaptable a distintos tamaños de pantalla.
 * - Separación clara entre lógica y UI.
 * - Uso de componentes de Material Design para mejor experiencia de usuario.
 */