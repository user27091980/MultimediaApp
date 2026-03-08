package com.example.multimediaapp.view.pages

// Importaciones necesarias de Compose
// Componentes y utilidades de tu proyecto
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.R
import com.example.multimediaapp.ui.theme.bandColumnModifier
import com.example.multimediaapp.view.components.BandHeader
import com.example.multimediaapp.view.components.BandTags
import com.example.multimediaapp.view.components.CardRowComponent
import com.example.multimediaapp.view.components.ImagesRowList
import com.example.multimediaapp.viewmodel.vm.BandVM

/**
 * Composable principal de la página de una banda
 * @author Andrés Canabal Poncela
 * @param bandId: ID de la banda para buscar en el ViewModel
 * @param vm: instancia del ViewModel que maneja los datos de bandas
 */
@Composable
fun BandScreen(
    bandId: String,
    vm: BandVM = viewModel()
) {

    // Observa el estado del ViewModel
    val uiState by vm.uiState.collectAsState()

    // Cargar datos una sola vez
    LaunchedEffect(Unit) {
        vm.loadData()
    }

    // Buscar banda actual
    val band = uiState.bands.find { it.id == bandId }

    band?.let { bd ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                // Puedes usar un color fijo o el color de fondo del tema
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

                ImagesRowList(
                    band = bd,
                    modifier = Modifier,

                    )
            }
        }
    }
}

@Preview
@Composable
fun BandScreenPreview() {
    BandScreen(
        bandId = "1",

        )
}





