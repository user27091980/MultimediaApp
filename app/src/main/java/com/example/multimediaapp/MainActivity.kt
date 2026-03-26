package com.example.multimediaapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.data.datastore.DataStoreManager
import com.example.multimediaapp.navigation.NavGraph
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.BottomBar
import com.example.multimediaapp.view.components.TopBar
import com.example.multimediaapp.viewmodel.vm.SettingsVM
import com.example.multimediaapp.viewmodel.vm.SettingsVMFactory

/**
 * Main activity of the application.
 *
 * Acts as the entry point of the app and is responsible for:
 * - Setting up the navigation graph
 * - Applying the global theme
 * - Managing the top and bottom bars
 * - Observing application settings
 *
 * Uses Jetpack Compose as the UI framework and follows an MVVM architecture.
 */
class MainActivity : ComponentActivity() {

    /**
     * Initializes the activity and sets up the Compose UI.
     *
     * Enables edge-to-edge rendering and configures the main scaffold
     * with navigation and UI components.
     */
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge layout (draw behind system bars)
        enableEdgeToEdge()

        setContent {
            // Crear DataStoreManager
            val dataStore = DataStoreManager(this)

            // Usar Factory para instanciar SettingsVM con parámetros
            val settingsVM: SettingsVM = viewModel(
                factory = SettingsVMFactory(dataStore)
            )

            val uiState by settingsVM.uiState.collectAsState()
            val navController = rememberNavController()

            MultimediaAppTheme(darkTheme = uiState.darkMode) {
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route ?: ""

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (currentRoute !in listOf(ObjRoutes.LOGINREG, ObjRoutes.REGISTER, ObjRoutes.LOGIN)) {
                            TopBar(navController = navController, dataStore = dataStore)
                        }
                    },
                    bottomBar = {
                        if (currentRoute !in listOf(ObjRoutes.LOGINREG, ObjRoutes.REGISTER, ObjRoutes.LOGIN)) {
                            BottomBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavGraph(navController = navController, settingsVM)
                    }
                }
            }
        }
    }
}

/*
Notas:

enableEdgeToEdge() nos permite que la UI se dibuje detrás de la barra de estado y navegación.

SettingsVM: Controla temas, modo oscuro y otras preferencias globales.

MultimediaAppTheme aplica los colores, tipografías y tema oscuro/claro.

NavController maneja la navegación entre pantallas.

currentRoute determina la pantalla actual para mostrar u ocultar top/bottom bars.

Scaffold es un componente de Material3 que facilita layout con top bar, bottom bar y contenido.

NavGraph ontiene todas las rutas y pantallas de la aplicación
 */
/**
 * MainActivity:
 *
 * Es la actividad principal y punto de entrada de la aplicación.
 * Se encarga de inicializar toda la UI usando Jetpack Compose
 * y de configurar los elementos globales como:
 *
 * - Tema de la aplicación
 * - Navegación
 * - Barras superiores e inferiores
 *
 * ARQUITECTURA GENERAL:
 *
 * MainActivity
 *     ↓
 * Theme (MultimediaAppTheme)
 *     ↓
 * Scaffold (estructura base UI)
 *     ↓
 * NavGraph (pantallas)
 *
 * onCreate():
 *
 * - Método principal que se ejecuta al iniciar la Activity.
 * - Aquí se configura toda la UI con setContent {}.
 *
 * enableEdgeToEdge():
 *
 * - Permite que la UI se dibuje detrás de las barras del sistema
 *   (status bar y navigation bar).
 * - Mejora la apariencia moderna de la app.
 *
 * VIEWMODEL:
 *
 * settingsVM:
 * - ViewModel global para gestionar preferencias (modo oscuro).
 *
 * uiState:
 * - Se obtiene con collectAsState().
 * - Permite que la UI reaccione automáticamente a cambios.
 *
 * TEMA:
 *
 * MultimediaAppTheme:
 *
 * - Aplica estilos globales (colores, tipografía, etc.).
 * - Usa darkTheme = uiState.darkMode para activar modo oscuro.
 *
 * NAVEGACIÓN:
 *
 * navController:
 * - Controlador principal de navegación.
 * - Se recuerda con rememberNavController().
 *
 * currentRoute:
 * - Obtiene la ruta actual desde el back stack.
 * - Se usa para mostrar/ocultar elementos UI.
 *
 * SCAFFOLD:
 *
 * - Componente base de Material3 que estructura la pantalla:
 *      • topBar
 *      • bottomBar
 *      • contenido principal
 *
 * TOP BAR:
 *
 * - Se muestra solo si NO estamos en:
 *      • Login
 *      • Register
 *      • Pantalla inicial
 *
 * - Se controla con:
 *
 *      if (currentRoute !in listOf(...))
 *
 * BOTTOM BAR:
 *
 * - Mismo comportamiento que TopBar.
 *
 * CONTENIDO:
 *
 * Box:
 * - Contenedor principal.
 * - Aplica padding interno del Scaffold.
 *
 * NavGraph:
 * - Define todas las pantallas y rutas de la app.
 * - Recibe:
 *      • navController (para navegar)
 *      • settingsVM (para acceso global a configuración)
 *
 * FLUJO GENERAL:
 *
 * 1. Se inicia la app → MainActivity.
 * 2. Se aplica el tema según preferencias.
 * 3. Se crea el NavController.
 * 4. Se renderiza el Scaffold.
 * 5. NavGraph muestra la pantalla actual.
 * 6. La UI cambia automáticamente según navegación o estado.
 *
 * BENEFICIOS:
 *
 * - Punto central de configuración de la app.
 * - Navegación desacoplada y organizada.
 * - UI reactiva gracias a StateFlow.
 * - Fácil control de elementos globales (TopBar / BottomBar).
 *
 * NOTAS:
 *
 * - collectAsState() permite recomposición automática.
 * - rememberNavController() mantiene el estado de navegación.
 * - currentBackStackEntryAsState() permite reaccionar a cambios de ruta.
 */