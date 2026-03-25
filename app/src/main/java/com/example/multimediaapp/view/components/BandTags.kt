package com.example.multimediaapp.view.components

// Importaciones necesarias de Compose
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.model.BandDTO


/**
 * Componente que muestra las etiquetas (tags) de una banda.
 * Incluye información como:
 * - Componentes del grupo
 * - Discográfica
 * - Estilo musical
 * - Discografía
 */
@Composable
fun BandTags(band: BandDTO) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface),
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
        shape = RoundedCornerShape(70),
        color = MaterialTheme.colorScheme.primary,
        tonalElevation = 40.dp
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        )
    }
}
/*
 * Este archivo define componentes reutilizables de la interfaz de usuario
 * en Jetpack Compose relacionados con la visualización de etiquetas (tags)
 * de una banda.
 *
 * Los componentes están diseñados para mostrar información de forma
 * visualmente clara y organizada dentro de la UI.
 *
 * COMPONENTE PRINCIPAL:
 *
 * BandTags(band: BandDTO)
 * - Recibe un objeto BandDTO con la información de la banda.
 * - Muestra diferentes etiquetas relacionadas con la banda:
 *   - components (miembros)
 *   - recordLabel (discográfica)
 *   - style (estilo musical)
 *
 * - Utiliza un Column para organizar el contenido verticalmente.
 * - Aplica un fondo y espaciado mediante modifiers.
 *
 * FlowRow:
 * - Permite organizar los elementos en forma de "chips" o etiquetas
 *   en varias filas automáticamente si no caben en una sola línea.
 * - Mejora la adaptación a diferentes tamaños de pantalla.
 *
 * TagItem(tag: String)
 * - Componente reutilizable que representa una etiqueta individual.
 * - Se utiliza para mostrar cada tag con estilo tipo "chip".
 * - Implementa:
 *   - Surface: contenedor con fondo y forma redondeada.
 *   - Text: muestra el contenido del tag.
 *
 * ESTILO VISUAL:
 * - Las etiquetas tienen forma redondeada (RoundedCornerShape).
 * - Se usa el color primario del tema para destacar los tags.
 * - Incluye padding interno para mejorar la legibilidad.
 *
 * VENTAJAS:
 * - Reutilización de componentes
 * - Mejor organización del código UI
 * - Separación clara entre lógica y presentación
 * - Fácil mantenimiento y escalabilidad
 *
 * Este tipo de componentes es fundamental en Compose para construir
 * interfaces modulares, limpias y reutilizables.
 */