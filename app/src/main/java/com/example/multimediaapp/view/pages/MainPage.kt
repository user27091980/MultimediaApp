package com.example.multimediaapp.view.pages

// Importaciones necesarias de Compose para layouts y estilos
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
// Importaciones de Material3 para usar el tema de la app
import androidx.compose.material3.MaterialTheme
// Importaciones básicas de Compose para funciones composables y estado
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
// Importaciones para modificar elementos visuales
import androidx.compose.ui.Modifier
// Importación para poder visualizar la pantalla en el Preview de Android Studio
import androidx.compose.ui.tooling.preview.Preview
// Permite obtener un ViewModel dentro de un Composable
import androidx.lifecycle.viewmodel.compose.viewModel
// Controlador de navegación de Jetpack Navigation
import androidx.navigation.NavController
// DTO que representa una banda en la pantalla principal
import com.example.multimediaapp.model.MainDTO
// Objeto donde se definen las rutas de navegación de la app
import com.example.multimediaapp.navigation.ObjRoutes
// Componente que muestra la lista de bandas en tarjetas
import com.example.multimediaapp.view.components.CardList
// Estado de UI que utiliza la pantalla principal
import com.example.multimediaapp.viewmodel.uistate.MainUiState
// ViewModel que gestiona la lógica y datos de la pantalla
import com.example.multimediaapp.viewmodel.vm.MainVM

/**
 * Pantalla principal de la aplicación
 *
 * Esta pantalla:
 * - Obtiene los datos desde el ViewModel
 * - Observa cambios en el estado de la UI
 * - Muestra una lista de bandas
 * - Permite navegar al detalle de cada banda
 */
@Composable
fun MainScreen(navController: NavController, viewModel: MainVM = viewModel()) {

    // Observamos el StateFlow del ViewModel.
    // collectAsState convierte el flujo en un estado observable por Compose.
    val uiState by viewModel.uiState.collectAsState()
    /*
    Box es un contenedor que permite superponer elementos.
    En este caso lo usamos simplemente como layout base.
    */

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()

    ) {
        /*
        Llamamos al composable que dibuja el contenido real
        separando la lógica de UI de la lógica de estado.
        */
        MainContent(
            uiState = uiState,
            // Cuando se pulse una banda se navegará a su pantalla de detalle
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

/**
 * Composable que dibuja la lista de bandas
 */
@Composable
fun MainContent(
    uiState: MainUiState,
    onImageClick: (String) -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Cargando...")
            }
        }

        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Error: ${uiState.error}")
            }
        }

        uiState.mainBands.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("No hay bandas")
            }
        }

        else -> {
            CardList(
                main = uiState.mainBands,
                onImageClick = { main ->
                    onImageClick(main.id)
                }
            )
        }
    }
}
/**
 * Preview de la pantalla principal.
 *
 * Permite visualizar la UI en Android Studio
 * sin necesidad de ejecutar la aplicación.
 */

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    /*
     Usamos datos de prueba (mock data)
     para simular las bandas que normalmente vendrían del ViewModel.
     */
    MainContent(
        uiState = MainUiState(
            mainBands = listOf(
                MainDTO("1", "Autechre", "https://via.placeholder.com/150"),
                MainDTO("2", "Aphex Twin", "https://via.placeholder.com/150"),
                MainDTO("3", "Boards of Canada", "https://via.placeholder.com/150")
            )
        ),
        // En el preview no necesitamos navegar
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