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
/*
 * Pantalla principal de la aplicación.
 *
 * FUNCIÓN PRINCIPAL:
 *
 * - Mostrar la lista de bandas disponibles.
 * - Gestionar los distintos estados de la UI:
 *   - Carga
 *   - Error
 *   - Lista vacía
 *   - Lista con datos
 * - Permitir navegar al detalle de cada banda.
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - MainVM gestiona la lógica y los datos.
 * - MainUiState representa el estado de la UI.
 *
 * FLUJO DE DATOS:
 *
 * 1. La pantalla observa el estado del ViewModel:
 *      uiState by viewModel.uiState.collectAsState()
 *
 * 2. Se cargan los datos al entrar en la pantalla:
 *      LaunchedEffect(Unit) { viewModel.loadData() }
 *
 * 3. Cuando cambia el estado:
 *      Compose vuelve a recomponer automáticamente la UI.
 *
 * ESTRUCTURA DE LA UI:
 *
 * MainScreen:
 * - Contenedor principal (Box).
 * - Aplica fondo con MaterialTheme.
 * - Llama a MainContent para renderizar el contenido.
 *
 * MainContent:
 * - Controla qué mostrar según el estado:
 *
 *   🔹 isLoading = true
 *   → Muestra "Cargando..."
 *
 *   🔹 error != null
 *   → Muestra el mensaje de error
 *
 *   🔹 Lista vacía
 *   → Muestra "No hay bandas"
 *
 *   🔹 Lista con datos
 *   → Muestra CardList con las bandas
 *
 * COMPONENTES:
 *
 * CardList:
 * - Componente reutilizable que muestra la lista de bandas.
 * - Recibe:
 *   - lista de bandas (mainBands)
 *   - callback onImageClick
 *
 * NAVEGACIÓN:
 *
 * navController.navigate("${ObjRoutes.BAND}/$bandId")
 * - Permite ir a la pantalla de detalle de la banda.
 * - Se envía el ID como parámetro.
 *
 * PREVIEW:
 *
 * MainScreenPreview:
 * - Permite visualizar la UI sin ejecutar la app.
 * - Usa datos simulados (fakeState).
 *
 * BENEFICIOS:
 *
 * - UI reactiva (se actualiza automáticamente).
 * - Separación de responsabilidades.
 * - Código modular y reutilizable.
 * - Manejo claro de estados (loading, error, data).
 *
 * NOTAS IMPORTANTES:
 *
 * - LaunchedEffect(Unit):
 *   Se ejecuta solo una vez al entrar en la pantalla.
 *
 * - collectAsState():
 *   Convierte un StateFlow en estado observable para Compose.
 *
 * - viewModel():
 *   Obtiene el ViewModel asociado al ciclo de vida.
 *
 * - El uso de MainUiState permite centralizar el estado de la UI.
 */