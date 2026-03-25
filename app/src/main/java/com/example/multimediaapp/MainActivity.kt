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
import com.example.multimediaapp.navigation.NavGraph
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.BottomBar
import com.example.multimediaapp.view.components.TopBar
import com.example.multimediaapp.viewmodel.vm.SettingsVM

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

            // ViewModel responsible for app settings
            val settingsVM: SettingsVM = viewModel()

            // Observe UI state reactively
            val uiState by settingsVM.uiState.collectAsState()

            // Apply global theme based on user settings
            MultimediaAppTheme(darkTheme = uiState.darkMode) {

                // Navigation controller
                val navController = rememberNavController()

                // Current route from navigation stack
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route ?: ""

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    // Top bar visible only in specific screens
                    topBar = {
                        if (currentRoute !in listOf(
                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN
                            )
                        ) {
                            TopBar(navController)
                        }
                    },

                    // Bottom bar visible only in specific screens
                    bottomBar = {
                        if (currentRoute !in listOf(
                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN
                            )
                        ) {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->

                    // Container for the main content
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        // Navigation graph of the application
                        NavGraph(
                            navController = navController,
                            settingsVM
                        )
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
