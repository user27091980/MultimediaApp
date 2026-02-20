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
 * @param bandMock: opción para pasar datos de prueba (útil para previews)
 */
@Composable
fun Band(
    bandId: String = "", // Por defecto vacío, útil para previews
    vm: BandVM = viewModel(),// ViewModel por defecto
    bandMock: BandUiState? = null///Datos de prueba para preview
) {
    // Estado local que guardará la banda que se debe mostrar
    var band by remember { mutableStateOf<BandUiState?>(null) }

    // Si no hay bandMock, cargamos los datos reales desde el ViewModel
    LaunchedEffect(key1 = bandId) {
        vm.loadData() // Llama al ViewModel para cargar todas las bandas
        // Buscar la banda por ID
        val bandState = vm.uiState.value.bands.find { it.id == bandId }// Busca la banda por ID
        band = bandState
    }

    // Usamos el bandMock si existe, sino usamos la banda cargada del ViewModel
    val bandToShow = bandMock ?: band
    // Renderizamos la UI solo si hay datos de banda
    bandToShow?.let { bd ->
        Column(
            bandColumnModifier
                .verticalScroll(rememberScrollState())// Scroll vertical para contenido
                .heightIn(min = 400.dp),// Altura mínima de la columna
            verticalArrangement = Arrangement.spacedBy(10.dp)// Espacio entre elementos
        )//separation between elements
        {
            BandHeader(band = bd) // Banner principal de la banda
            Spacer(modifier = Modifier.height(6.dp))// Separador vertical
            CardRowComponent(band = bd)// Card con info detallada de la banda
            Spacer(modifier = Modifier.height(6.dp))
            BandTags(band = bd)// Etiquetas de estilo, discografía, etc.
            Spacer(modifier = Modifier.height(6.dp))
            ImagesRowList(band = bd)// Fila horizontal con imágenes de álbumes

        }
    }
}

/**
 * Preview de la página Band con datos de prueba
 */

@Preview(showBackground = true) // Muestra fondo en el preview
@Composable
fun BandPrev() {

    val sampleBand = BandUiState(
        id = "0",
        name = "Tool",
        textInfo = "Banda de metal progresivo estadounidense",
        headerImage = "ApiGenerica/data/resources/tool.jpg",
        albumImages = listOf(
            "data/resources/tool3.jpg",
            "data/resources/tool4.jpg",
            "data/resources/tool5.jpg",
            "data/resources/tool6.jpg",
            "data/resources/tool7.jpg",
            "data/resources/tool8.jpg"
        ),
        style = "Metal progresivo",
        recordLabel = "BGM",
        components = "Maynard, Danny, Adam, Justin",
        discography = listOf("Opiate", "Undertow", "Ænima"),
        imageBand = "data/resources/tool1.jpg"
    )

    Band(
        bandMock = sampleBand,
    )
}


