package com.example.multimediaapp.view.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.view.components.CardList
import com.example.multimediaapp.viewmodel.vm.MainVM

@Composable
fun MainScreen(navController: NavController, vm: MainVM = viewModel()) {

    // LaunchedEffect se ejecuta cuando el Composable entra en composición.
    // Unit como clave significa que se ejecutará solo una vez.
    // Aquí llamamos a loadData() para cargar las bandas desde el ViewModel.
    LaunchedEffect(Unit) {
        vm.loadData()
    }
    // collectAsState() convierte el StateFlow del ViewModel en un State observable por Compose.
    // Cuando uiState cambie, la UI se recompondrá automáticamente.
    val uiState = vm.uiState.collectAsState()
    // Llamamos al componente que muestra la lista de bandas.
    // Accedemos a .value porque collectAsState() devuelve un State<T>.
    CardList(
        bands = uiState.value.mainBands,
        onImageClick = {
            // Aquí puedes navegar o mostrar detalles
            ObjRoutes.BAND
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val navController = rememberNavController()

    MainScreen(
        navController = navController
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