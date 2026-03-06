package com.example.multimediaapp.view.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.network.MultimediaApiService
import com.example.multimediaapp.view.components.CardList
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import com.example.multimediaapp.viewmodel.vm.MainVM


@Composable
fun MainScreen(navController: NavController, vm: MainVM = viewModel()) {
    val api = MultimediaApiService.create()
    val vm: MainVM = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainVM(api) as T
            }
        }
    )

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
    MainContent(
        uiState = uiState,
        onImageClick = { bandId ->
            navController.navigate("${ObjRoutes.BAND}/$bandId")
        }
    )

}

@Composable
fun MainContent(
    uiState: MainUiState,
    onImageClick: (String) -> Unit
) {
    CardList(
        bands = uiState.mainBands,
        onImageClick = { band ->
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