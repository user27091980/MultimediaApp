package com.example.multimediaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multimediaapp.network.MultimediaApiService
import com.example.multimediaapp.view.pages.*
import com.example.multimediaapp.viewmodel.vm.*

/**
 * Función Composable que define la navegación de la aplicación.
 *
 * NavHostController se encarga de manejar las rutas y el historial de navegación.
 * Cada "composable" representa una pantalla de la app.
 */
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ObjRoutes.LOGINREG
    ) {

        // Pantalla inicial Login / Register
        composable(ObjRoutes.LOGINREG) {
            val vm: LoginRegVM = viewModel()
            LoginRegScreen(navController = navController, vm = vm)
        }

        // Pantalla de login
        composable(ObjRoutes.LOGIN) {
            val vm: LoginVM = viewModel()
            LoginScreen(navController = navController, vm = vm)
        }

        // Pantalla de registro
        composable(ObjRoutes.REGISTER) {
            val vm: RegisterVM = viewModel()
            RegisterScreen(navController = navController, vm = vm)
        }

        // Pantalla principal
        composable(ObjRoutes.MAIN) {
            val vm: MainVM = viewModel() // No necesita factory
            MainScreen(navController = navController, vm = vm)
        }

        // Pantalla de banda con parámetro
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            val bandId = backStackEntry.arguments?.getString("bandId") ?: "default"
            val vm: BandVM = viewModel()
            BandScreen(bandId = bandId, vm = vm)
        }

        // Información del usuario
        composable(ObjRoutes.INFOUSER) {
            val vm: UserInfoVM = viewModel()
            val userId = "defaultUser"
            UserInfoScreen(userInfoId = userId, vm = vm)
        }

        // Configuración
        composable(ObjRoutes.SETTINGS) {
            SettingsScreen()
        }

        // Dialog
        composable(ObjRoutes.DIALOG) {
            DialogRegisterScreen(navController = navController)
        }
    }
}

/*
NavHost es el punto central de la navegación en Jetpack Compose; define rutas y pantalla inicial.

composable(route):define cada pantalla asociada a una ruta.

ViewModels con viewModel() se instancian y se ligan automáticamente al ciclo de vida de la pantalla;
 no necesitan factories si no reciben parámetros.

Rutas con parámetros como "${ObjRoutes.BAND}/{bandId}", se usan para pasar información a la pantalla de detalle.

Callbacks y NavController  se pasa a cada pantalla para manejar navegación interna
(por ejemplo, botones de “volver” o “ir a otra pantalla”).
 */