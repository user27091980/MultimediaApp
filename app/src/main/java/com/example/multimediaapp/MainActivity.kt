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
 * MainActivity: Actividad principal de la app que contiene la navegación,
 * top bar, bottom bar y aplica el tema según la configuración de usuario.
 */

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el modo Edge-to-Edge (contenido se dibuja detrás de status & navigation bars)
        enableEdgeToEdge()
        // Composable principal de la app
        setContent {
            // Instanciamos el ViewModel de Settings
            val settingsVM: SettingsVM = viewModel()
            // Observamos el estado actual del ViewModel
            val uiState by settingsVM.uiState.collectAsState()

            // Aplicamos el tema de la app, ajustando modo oscuro según SettingsVM
            MultimediaAppTheme(darkTheme = uiState.darkMode) {
                // Controlador de navegación para Compose Navigation
                val navController = rememberNavController()
                // Obtenemos la ruta actual del backstack
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route

                // Scaffold principal: maneja topBar, bottomBar y contenido central
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    //TopBar personalizada: se oculta en pantallas de login/registro
                    topBar = {
                        if (currentRoute !in listOf(

                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN
                            )
                        ) TopBar(navController)
                    },
                    // BottomBar personalizada: se oculta en pantallas de login/registro
                    bottomBar = {
                        if (currentRoute !in listOf(
                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN

                            )
                        ) {
                            BottomBar(navController)
                        }
                    },
                    // Contenido principal de la app
                    content = { innerPadding ->
                        // Box para envolver el contenido y aplicar padding automático de Scaffold
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            // NavGraph contiene toda la navegación interna de la app
                            NavGraph(
                                navController = navController,
                                settingsVM
                            )
                        }
                    }
                )
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
