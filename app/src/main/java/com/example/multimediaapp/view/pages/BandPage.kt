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
 * Pantalla que muestra los detalles de una banda.
 *
 * Esta pantalla obtiene los datos desde el ViewModel, busca una banda
 * por su identificador y muestra su información en diferentes secciones.
 *
 * Si la banda no existe, se muestra un mensaje informativo.
 *
 * @param bandId Identificador de la banda a mostrar.
 * @param vm ViewModel que contiene y gestiona la lista de bandas.
 */
@Composable
fun BandScreen(
    bandId: String,
    vm: BandVM = viewModel()
) {

    /**
     * Lista de bandas observada desde el ViewModel.
     */
    val bands by vm.bandsState.collectAsState(initial = emptyList())

    /**
     * Carga inicial de datos cuando se crea la pantalla.
     */
    LaunchedEffect(Unit) {
        vm.loadAllBands()
    }

    /**
     * Banda seleccionada a partir del identificador recibido.
     */
    val band = bands.find { it.id == bandId }

    if (band == null) {

        /**
         * Pantalla mostrada cuando no se encuentra la banda.
         */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.info))
        }

    } else {

        /**
         * Pantalla principal con los datos de la banda.
         */
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

                /**
                 * Cabecera con imagen y nombre de la banda.
                 */
                BandHeader(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                /**
                 * Información principal en formato tarjeta.
                 */
                CardRowComponent(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                /**
                 * Texto de sección informativa.
                 */
                Text(text = stringResource(R.string.info))

                Spacer(modifier = Modifier.height(8.dp))

                /**
                 * Etiquetas o categorías de la banda.
                 */
                BandTags(band = band)

                Spacer(modifier = Modifier.height(8.dp))

                /**
                 * Sección de discografía.
                 */
                Text(text = stringResource(R.string.discografia))

                Spacer(modifier = Modifier.height(8.dp))

                /**
                 * Lista de imágenes del álbum de la banda.
                 */
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
 * Vista previa de la pantalla BandScreen.
 *
 * Se utiliza una instancia de [BandDTO] de ejemplo para mostrar
 * la interfaz sin necesidad de ViewModel ni datos reales.
 */
@Preview(showBackground = true)
@Composable
fun BandScreenPreview() {

    /**
     * Datos de ejemplo para la previsualización.
     */
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

    /**
     * UI de previsualización sin ViewModel.
     */
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
 * Este archivo define la pantalla de detalle de una banda (BandScreen)
 * utilizando Jetpack Compose.
 *
 * OBJETIVO:
 *
 * - Mostrar toda la información de una banda seleccionada.
 * - Obtener los datos desde un ViewModel.
 * - Presentar la información organizada en secciones visuales.
 *
 * FLUJO DE DATOS:
 *
 * 1. Se recibe el bandId como parámetro.
 * 2. Se obtienen las bandas desde el ViewModel (BandVM).
 * 3. Se busca la banda correspondiente con:
 *      bands.find { it.id == bandId }
 * 4. Si no existe → se muestra mensaje de error.
 * 5. Si existe → se muestra la información completa.
 *
 * ESTADO:
 *
 * bands:
 * - Se obtiene con collectAsState().
 * - Representa la lista reactiva de bandas.
 *
 * LaunchedEffect:
 * - Ejecuta una acción cuando la pantalla se crea.
 * - En este caso, carga todas las bandas:
 *      vm.loadAllBands()
 *
 * UI PRINCIPAL:
 *
 * 1. Box:
 *    - Contenedor principal.
 *    - Permite controlar fondo y alineación.
 *
 * 2. Column:
 *    - Organiza el contenido verticalmente.
 *    - Permite scroll con verticalScroll().
 *
 * SECCIONES DE LA PANTALLA:
 *
 * - BandHeader:
 *   Muestra imagen principal y nombre de la banda.
 *
 * - CardRowComponent:
 *   Muestra información en tarjetas (descripcion).
 *
 * - BandTags:
 *   Muestra etiquetas como estilo, discográfica y componentes.
 *
 * - ImagesRowList:
 *   Muestra imágenes de álbumes en formato horizontal.
 *
 * - Text (Info / Discografía):
 *   Separadores de secciones usando textos de recursos.
 *
 * - Spacer:
 *   Añade espacio visual entre componentes.
 *
 * CASO SIN DATOS:
 *
 * - Si band == null:
 *   → Se muestra una pantalla simple con un mensaje informativo.
 *
 * PREVIEW:
 *
 * BandScreenPreview:
 * - Permite visualizar la UI sin necesidad de backend.
 * - Usa un objeto BandDTO de ejemplo (mock).
 *
 * BENEFICIOS:
 *
 * - Separación entre UI y lógica (MVVM).
 * - Uso de componentes reutilizables.
 * - UI reactiva basada en estado.
 * - Fácil de testear y mantener.
 *
 * NOTA:
 *
 * - El ViewModel (BandVM) gestiona los datos.
 * - Esta pantalla solo se encarga de mostrar la información.
 */