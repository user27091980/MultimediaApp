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
 * Componente BandTags:
 *
 * Muestra un conjunto de etiquetas (tags) relacionadas con una banda.
 * Se utiliza principalmente en la pantalla de detalle de la banda.
 *
 * Datos mostrados:
 * - Componentes del grupo
 * - Discográfica (recordLabel)
 * - Estilo musical (style)
 *
 * Arquitectura UI:
 * - Composable principal: BandTags
 * - Reutilizable: TagItem (cada etiqueta individual)
 *
 * ===Notas de implementación===
 * - Column: organiza los elementos verticalmente con separación de 10dp.
 * - FlowRow: permite que los tags fluyan horizontalmente y se ajusten automáticamente
 *   a la siguiente línea si no caben.
 * - TagItem: representa un chip con:
 *    - Fondo redondeado (RoundedCornerShape)
 *    - Color principal del tema (MaterialTheme.colorScheme.primary)
 *    - Padding interno para estética
 * - TonalElevation: crea un ligero efecto de sombra sobre la superficie.
 *
 * ===Ventajas de este enfoque===
 * 1. Reutilizable: TagItem se puede usar en otros contextos.
 * 2. Flexible: FlowRow maneja automáticamente el ajuste de etiquetas.
 * 3. Modular: Separación clara entre el layout (BandTags) y el estilo visual de cada chip (TagItem).
 *
 * ===Ejemplo de uso===
 * ```
 * val band = BandDTO(
 *     id = "1",
 *     name = "The Example Band",
 *     description = "Rock clásico",
 *     banner = "url_banner",
 *     albumImages = listOf("img1","img2"),
 *     style = "Rock",
 *     recordLabel = "BigLabel",
 *     components = "Voz, Guitarra, Bajo, Batería",
 *     albumLinks = listOf("link1", "link2"),
 *     headerLink = "header_url"
 * )
 *
 * BandTags(band = band)
 * ```
 *
 * Esto generará una fila de chips con los componentes, discográfica y estilo.
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
 * Componente TagItem:
 *
 * Representa un chip individual que muestra un texto con fondo redondeado.
 *
 * Propiedades:
 * - tag: texto a mostrar en la etiqueta
 * - shape: RoundedCornerShape(70) para apariencia de píldora
 * - color: color principal del tema
 * - tonalElevation: crea efecto de profundidad
 * - padding: espaciado interno horizontal y vertical
 *
 * Ventaja: se puede reutilizar para cualquier otro tag o categoría en la app.
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