package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.R
import com.example.multimediaapp.ui.theme.bandColumnModifier
import com.example.multimediaapp.view.components.BandHeader
import com.example.multimediaapp.view.components.BandTags
import com.example.multimediaapp.view.components.CardRowComponent
import com.example.multimediaapp.view.components.ImagesRowList
import com.example.multimediaapp.viewmodel.vm.BandVM

@Composable
fun BandScreen(
    bandId: String,
    vm: BandVM = viewModel()
) {
    // Observa la lista de bandas del ViewModel
    val bands by vm.bandsState.collectAsState()

    // Cargar datos una sola vez
    LaunchedEffect(Unit) {
        vm.loadAllBands()
    }

    // Buscar banda actual
    val band = bands.find { it.id == bandId }

    band?.let { bd ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                bandColumnModifier
                    .verticalScroll(rememberScrollState())
                    .heightIn(min = 400.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BandHeader(band = bd)

                Spacer(modifier = Modifier.height(6.dp))
                CardRowComponent(band = bd)

                Spacer(modifier = Modifier.height(6.dp))
                Text(stringResource(R.string.info))

                Spacer(modifier = Modifier.height(6.dp))
                BandTags(band = bd)

                Spacer(modifier = Modifier.height(6.dp))
                Text(stringResource(R.string.discografia))

                Spacer(modifier = Modifier.height(6.dp))
                ImagesRowList(band = bd, modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
fun BandScreenPreview() {
    BandScreen(bandId = "1")
}
