package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.R
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.ui.theme.bandColumnModifier
import com.example.multimediaapp.ui.viewmodel.BandVM
import com.example.multimediaapp.view.components.BandHeader
import com.example.multimediaapp.view.components.BandTags
import com.example.multimediaapp.view.components.CardRowComponent
import com.example.multimediaapp.view.components.ImagesRowList

/**
 * BandScreen:
 *
 * Pantalla principal que muestra los detalles completos de una banda.
 * Se obtiene la información desde un ViewModel ([BandVM]) y se filtra la banda
 * por su identificador [bandId]. La pantalla está dividida en secciones:
 *
 * - Cabecera con imagen y nombre de la banda.
 * - Información principal en formato de tarjetas.
 * - Texto informativo.
 * - Etiquetas/categorías de la banda.
 * - Discografía (lista de álbumes con imágenes).
 *
 * Si la banda no se encuentra, se muestra un mensaje de información.
 *
 * @param bandId Identificador de la banda a mostrar.
 * @param vm ViewModel que maneja la lista de bandas. Se obtiene por defecto usando `viewModel()`.
 */
@Composable
fun BandScreen(
    bandId: String,
    vm: BandVM = viewModel()
) {

    // Observa la lista de bandas desde el estado del ViewModel
    val bands by vm.bandsState.collectAsState(initial = emptyList())

    // Carga inicial de los datos al crear la pantalla
    LaunchedEffect(Unit) {
        vm.loadAllBands()
    }

    // Busca la banda seleccionada por su id
    val band = bands.find { it.id == bandId }

    if (band == null) {
        // Pantalla de "no encontrada"
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.info))
        }

    } else {
        // Pantalla principal con la información de la banda
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = bandColumnModifier
                    .verticalScroll(rememberScrollState()) // Permite scroll vertical
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp) // Espaciado entre secciones
            ) {

                // Cabecera con imagen y nombre
                BandHeader(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                // Información principal en formato tarjeta
                CardRowComponent(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                // Texto de información general
                Text(text = stringResource(R.string.info))

                Spacer(modifier = Modifier.height(8.dp))

                // Etiquetas de estilo, componentes, o categoría de la banda
                BandTags(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                // Sección de discografía
                Text(text = stringResource(R.string.discografia))

                Spacer(modifier = Modifier.height(8.dp))

                // Lista horizontal de imágenes de álbumes
                ImagesRowList(
                    band = band,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

/**
 * BandScreenPreview:
 *
 * Permite previsualizar la UI de BandScreen sin necesidad de ViewModel ni datos reales.
 * Se crea una instancia de [BandDTO] con información de ejemplo.
 *
 * Esto facilita el diseño y pruebas visuales dentro de Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun BandScreenPreview() {

    val defaultBand = BandDTO(
        id = "1",
        name = "Coldplay",
        description = "Banda británica de rock alternativo formada en Londres.",
        banner = "https://via.placeholder.com/600x300",
        albumImages = listOf(
            "https://via.placeholder.com/150",
            "https://via.placeholder.com/150"
        ),
        style = "Rock Alternativo",
        recordLabel = "Parlophone",
        components = "Chris Martin, Jonny Buckland, Guy Berryman, Will Champion",
        albumLinks = listOf(
            "https://example.com/album1",
            "https://example.com/album2"
        ),
        headerLink = "https://example.com"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = bandColumnModifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BandHeader(band = defaultBand)
            Spacer(modifier = Modifier.height(8.dp))
            CardRowComponent(band = defaultBand)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Info")
            Spacer(modifier = Modifier.height(8.dp))
            BandTags(band = defaultBand)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Discografía")
            Spacer(modifier = Modifier.height(8.dp))
            ImagesRowList(
                band = defaultBand,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/*
Notas importantes:

1. bandColumnModifier:
   - Modificador de columna definido en el tema de UI.
   - Controla padding, tamaño y estilo de toda la columna principal.

2. verticalScroll + rememberScrollState:
   - Permite que la pantalla sea scrollable verticalmente.
   - Importante para dispositivos con pantallas pequeñas.

3. LaunchedEffect(Unit):
   - Se ejecuta al crear la pantalla para cargar datos iniciales desde el ViewModel.

4. Uso de Composables modulares:
   - BandHeader: muestra imagen y nombre.
   - CardRowComponent: muestra información principal en tarjetas.
   - BandTags: muestra etiquetas o categorías.
   - ImagesRowList: lista horizontal de imágenes de álbumes.

5. Previsualización:
   - BandScreenPreview permite ver cómo se verá la UI con datos de ejemplo sin ejecutar la app.
*/