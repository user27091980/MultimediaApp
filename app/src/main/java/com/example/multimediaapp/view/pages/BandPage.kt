package com.example.multimediaapp.view.pages

// Importaciones necesarias de Compose
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
// Componentes y utilidades de tu proyecto
import com.example.multimediaapp.ui.theme.bandColumnModifier
import com.example.multimediaapp.view.components.BandHeader
import com.example.multimediaapp.view.components.BandTags
import com.example.multimediaapp.view.components.CardRowComponent
import com.example.multimediaapp.view.components.ImagesRowList
import com.example.multimediaapp.viewmodel.uistate.BandUiState
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

            BandTags(band = bd)

            Spacer(modifier = Modifier.height(6.dp))

            ImagesRowList(
                band = bd,
                modifier = Modifier,
                onImageClick = { /* TODO navegación */ }
            )
        }
    }
}








