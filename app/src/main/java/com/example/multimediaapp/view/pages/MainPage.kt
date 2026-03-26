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
 * Pantalla principal de la aplicación.
 *
 * Se encarga de:
 * - Obtener los datos desde el ViewModel
 * - Observar el estado de la UI
 * - Mostrar una lista de bandas
 * - Gestionar la navegación al detalle de cada banda
 *
 * @param navController Controlador de navegación para cambiar de pantalla.
 * @param viewModel ViewModel que gestiona los datos y la lógica de la pantalla.
 */
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainVM = viewModel()
) {

    /**
     * Estado observable de la UI proveniente del ViewModel.
     */
    val uiState by viewModel.uiState.collectAsState()

    /**
     * Carga inicial de datos al crear la pantalla.
     */
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    /**
     * Contenedor principal de la pantalla.
     */
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {

        /**
         * Contenido principal de la pantalla.
         */
        MainContent(
            uiState = uiState,
            onImageClick = { bandId ->
                navController.navigate("${ObjRoutes.BAND}/$bandId")
            }
        )
    }
}

/**
 * Composable que representa el contenido principal de la pantalla.
 *
 * Se encarga de renderizar la UI en función del estado recibido.
 *
 * @param uiState Estado actual de la pantalla.
 * @param onImageClick Callback que se ejecuta al pulsar una banda.
 * Recibe el identificador de la banda seleccionada.
 */
@Composable
fun MainContent(
    uiState: MainUiState,
    onImageClick: (String) -> Unit
) {
    when {

        /**
         * Estado de carga.
         */
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Cargando...")
            }
        }

        /**
         * Estado de error.
         */
        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Error: ${uiState.error}")
            }
        }

        /**
         * Estado sin datos.
         */
        uiState.mainBands.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("No hay bandas")
            }
        }

        /**
         * Estado con datos disponibles.
         */
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
 * Vista previa de la pantalla principal.
 *
 * Permite visualizar la UI sin necesidad de ejecutar la app.
 */
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    /**
     * Datos simulados para el preview.
     */
    val fakeState = MainUiState(
        mainBands = listOf(
            MainDTO("1", "Autechre", "https://via.placeholder.com/150"),
            MainDTO("2", "Aphex Twin", "https://via.placeholder.com/150"),
            MainDTO("3", "Boards of Canada", "https://via.placeholder.com/150")
        )
    )

    /**
     * Contenido del preview.
     */
    MainContent(
        uiState = fakeState,
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