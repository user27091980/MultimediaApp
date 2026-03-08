package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.CardList
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import com.example.multimediaapp.viewmodel.vm.MainVM


@Composable
fun MainScreen(navController: NavController, vm: MainVM = viewModel()) {


    val uiState by vm.uiState.collectAsState()
    // LaunchedEffect se ejecuta cuando el Composable entra en composición.
    // Unit como clave significa que se ejecutará solo una vez.
    // Aquí llamamos a loadData() para cargar las bandas desde el ViewModel.
    LaunchedEffect(Unit) {
        vm.loadData()
    }
    // collectAsState() convierte el StateFlow del ViewModel en un State observable por Compose.
    // Cuando uiState cambie, la UI se recompondrá automáticamente.
    // Observamos el StateFlow con valor inicial para evitar errores

    // Llamamos al componente que muestra la lista de bandas.
    // Accedemos a .value porque collectAsState() devuelve un State<T>.

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()

    ) {
        MainContent(
            uiState = uiState,
            onImageClick = { bandId ->
                navController.navigate("${ObjRoutes.BAND}/$bandId")
            }
        )
    }
}
/**
 * Composable encargado de mostrar el contenido principal de la pantalla.
 *
 * Separa la lógica de UI de MainScreen para mantener la arquitectura más limpia
 * y facilitar el uso de previews o pruebas.
 *
 * @param uiState Estado actual de la pantalla principal.
 * Contiene la lista de bandas obtenidas desde el ViewModel.
 *
 * @param onImageClick Lambda que se ejecuta cuando el usuario pulsa
 * sobre la imagen de una banda. Recibe el id de la banda seleccionada
 * para poder navegar a su pantalla de detalle.
 */

@Composable
fun MainContent(
    uiState: MainUiState,
    onImageClick: (String) -> Unit
) {
    // CardList muestra una lista vertical con las bandas disponibles.
    // Recibe la lista desde uiState y un callback para manejar el click.
    CardList(
        bands = uiState.mainBands,
        onImageClick = { band ->
            // Cuando se pulsa una banda, se envía su id al callback
            // para que la pantalla superior gestione la navegación.
            onImageClick(band.id)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    // Now the preview uses the stateless MainContent with mock data
    // avoiding the ViewModel instantiation error.
    MainContent(
        uiState = MainUiState(
            mainBands = listOf(
                MainDTO("1", "Autechre", "https://via.placeholder.com/150"),
                MainDTO("2", "Aphex Twin", "https://via.placeholder.com/150"),
                MainDTO("3", "Boards of Canada", "https://via.placeholder.com/150")
            )
        ),
        onImageClick = {}
    )
}
/**
 * Teoría:
 *
 * viewModel()
 *
 * Obtiene la instancia del BandVM asociada al ciclo de vida actual (Activity o NavGraph).
 *
 * LaunchedEffect(Unit)
 *
 * Se ejecuta una sola vez.
 *
 * Ideal para cargar datos iniciales.
 *
 * Evita que loadData() se llame en cada recomposición.
 *
 * collectAsState()
 *
 * Convierte un StateFlow en un estado observable por Compose.
 * Cada vez que cambie uiState, la pantalla se vuelve a dibujar automáticamente.
 *
 * CardList
 *
 * Recibe la lista bands y muestra cada banda (según lo implementado en tu componente).
 */