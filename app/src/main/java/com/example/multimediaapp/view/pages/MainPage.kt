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
 * MainScreen:
 *
 * Pantalla principal de la aplicación que muestra la lista de bandas.
 *
 * Funcionalidades principales:
 * - Obtiene los datos desde el ViewModel.
 * - Observa el estado de la UI (cargando, error, vacío o con datos).
 * - Muestra la lista de bandas mediante tarjetas.
 * - Gestiona la navegación al detalle de cada banda usando su ID.
 *
 * @param navController Controlador de navegación para cambiar de pantalla.
 * @param viewModel ViewModel que gestiona los datos y la lógica de la pantalla.
 */
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainVM = viewModel() // Obtiene automáticamente el ViewModel asociado al ciclo de vida
) {

    // Estado observable de la UI proveniente del ViewModel.
    val uiState by viewModel.uiState.collectAsState()

    // Carga inicial de datos cuando se crea la pantalla.
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    // Contenedor principal de la pantalla con fondo secundario del tema.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {

        // Contenido principal de la pantalla.
        MainContent(
            uiState = uiState,
            onImageClick = { bandId ->
                // Navega a la pantalla de detalle de la banda con el ID correspondiente
                navController.navigate("${ObjRoutes.BAND}/$bandId")
            }
        )
    }
}

/**
 * MainContent:
 *
 * Composable que representa el contenido central de MainScreen.
 *
 * Renderiza la UI en función del estado recibido desde el ViewModel.
 *
 * @param uiState Estado actual de la pantalla.
 * @param onImageClick Callback que se ejecuta al pulsar una banda. Devuelve el ID de la banda.
 */
@Composable
fun MainContent(
    uiState: MainUiState,
    onImageClick: (String) -> Unit
) {
    when {
        // Estado de carga: se muestra un texto informativo
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Cargando...")
            }
        }

        // Estado de error: se muestra el mensaje de error
        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("Error: ${uiState.error}")
            }
        }

        // Estado sin datos: la lista de bandas está vacía
        uiState.mainBands.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Text("No hay bandas")
            }
        }

        // Estado con datos disponibles: se muestra la lista de bandas
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

    // Datos simulados para el preview
    val fakeState = MainUiState(
        mainBands = listOf(
            MainDTO("1", "Autechre", "https://via.placeholder.com/150"),
            MainDTO("2", "Aphex Twin", "https://via.placeholder.com/150"),
            MainDTO("3", "Boards of Canada", "https://via.placeholder.com/150")
        )
    )

    // Renderizado del contenido con datos de ejemplo
    MainContent(
        uiState = fakeState,
        onImageClick = {}
    )
}

/**
 * Teoría / Notas:
 *
 * 1. **viewModel()**
 *    - Obtiene la instancia del MainVM asociada al ciclo de vida actual (Activity o NavGraph).
 *
 * 2. **LaunchedEffect(Unit)**
 *    - Se ejecuta una sola vez al crear la pantalla.
 *    - Ideal para cargar datos iniciales y evitar que loadData() se llame en cada recomposición.
 *
 * 3. **collectAsState()**
 *    - Convierte un StateFlow en un estado observable por Compose.
 *    - Cada vez que cambia uiState, la UI se vuelve a dibujar automáticamente.
 *
 * 4. **Box**
 *    - Contenedor que permite posicionar elementos y superponerlos.
 *
 * 5. **CardList**
 *    - Componente personalizado que recibe la lista de bandas y muestra cada una en tarjetas.
 *
 * 6. **onImageClick**
 *    - Callback que permite navegar a la pantalla de detalle de la banda seleccionada usando su ID.
 */