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
import com.example.multimediaapp.ui.theme.bandColumnModifier
import com.example.multimediaapp.ui.viewmodel.BandVM
import com.example.multimediaapp.view.components.BandHeader
import com.example.multimediaapp.view.components.BandTags
import com.example.multimediaapp.view.components.CardRowComponent
import com.example.multimediaapp.view.components.ImagesRowList

@Composable
fun BandScreen(
    bandId: String,
    vm: BandVM = viewModel()
) {
    // Observa la lista de bandas del ViewModel
    val bands by vm.bandsState.collectAsState(initial = emptyList())

    // Cargar datos al iniciar
    LaunchedEffect(Unit) {
        vm.loadAllBands()
    }

    // Buscar banda actual
    val band = bands.find { it.id == bandId }

    if (band == null) {
        // Puedes mostrar un loading o mensaje de "no encontrada"
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.info)) // placeholder
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = bandColumnModifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp), // agrega padding para que no toque bordes
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BandHeader(band = band)

                CardRowComponent(band = band)

                Text(text = stringResource(R.string.info))

                BandTags(band = band)

                Text(text = stringResource(R.string.discografia))

                ImagesRowList(band = band, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

