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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.NavGraph
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.BottomBar
import com.example.multimediaapp.view.components.TopBar
import com.example.multimediaapp.viewmodel.vm.SettingsVM
import com.example.multimediaapp.viewmodel.vm.SettingsVMFactory

/**
 * MainActivity:
 *
 * Actividad principal de la aplicación que sirve como punto de entrada.
 *
 * Funciones principales:
 * 1. Configura la interfaz con Jetpack Compose.
 * 2. Aplica un tema global, adaptándose al modo oscuro según la configuración.
 * 3. Maneja la navegación entre pantallas usando [NavController] y [NavGraph].
 * 4. Controla la visibilidad de la [TopBar] y [BottomBar] dependiendo de la ruta actual.
 * 5. Observa de manera reactiva el estado de [SettingsVM] para cambios en preferencias de usuario.
 *
 * Arquitectura:
 * - MVVM: La UI observa cambios en el estado de SettingsVM.
 * - Material3: Uso de [Scaffold] para layout estructurado con top bar, bottom bar y contenido.
 */
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // 1. Inicializamos el DataStoreManager aquí para poder pasárselo al ViewModel
            // En MainActivity.kt
            val context = androidx.compose.ui.platform.LocalContext.current
            val dataStore = remember { DataStoreManager(context) }

// Ahora ya puedes usar la Factory sin errores
            val settingsVM: SettingsVM = viewModel(
                factory = SettingsVMFactory(dataStore)
            )


            // 3. Observamos el estado (que ahora estará conectado al DataStore)
            val uiState by settingsVM.uiState.collectAsState()

            // Aplica el tema global usando el valor persistente
            MultimediaAppTheme(darkTheme = uiState.darkMode) {

                val navController = rememberNavController()
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route ?: ""

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavGraph(
                            navController = navController,
                            settingsVM = settingsVM
                        )
                    }
                }
            }
        }
    }
}

/*
Notas importantes:

- enableEdgeToEdge(): Permite que la UI se dibuje detrás de las barras de sistema.
- SettingsVM: Gestiona preferencias globales (ej. modo oscuro) y expone el estado a la UI.
- MultimediaAppTheme: Aplica colores, tipografía y modo oscuro/claro.
- NavController: Controla la navegación entre pantallas.
- currentRoute: Determina la pantalla actual para mostrar u ocultar top/bottom bars.
- Scaffold: Layout de Material3 que permite top bar, bottom bar y contenido principal.
- NavGraph: Contiene todas las rutas y composables de la aplicación.
*/