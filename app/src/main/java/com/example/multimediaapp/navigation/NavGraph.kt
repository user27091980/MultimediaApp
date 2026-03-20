package com.example.multimediaapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.multimediaapp.ui.viewmodel.BandVM
import com.example.multimediaapp.view.pages.*
import com.example.multimediaapp.viewmodel.vm.*

/**
 * Función Composable que define la navegación de la aplicación.
 *
 * NavHostController se encarga de manejar las rutas y el historial de navegación.
 * Cada "composable" representa una pantalla de la app.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    settingsVM: SettingsVM
) {
    NavHost(
        navController = navController,
        startDestination = ObjRoutes.LOGINREG
    ) {

        // Pantalla inicial
        composable(ObjRoutes.LOGINREG) {
            LoginRegScreen(navController = navController)
        }

        // LOGIN
        composable(ObjRoutes.LOGIN) {
            val vm: UserVM = viewModel()

            LoginScreen(
                navController = navController,
                vm = vm
            )
        }

        // REGISTER
        composable(ObjRoutes.REGISTER) {
            val vm: UserVM = viewModel()

            RegisterScreen(
                navController = navController,
                vm = vm
            )
        }

        // MAIN
        composable(ObjRoutes.MAIN) {
            val vm: MainVM = viewModel()

            MainScreen(
                navController = navController,
                viewModel = vm
            )
        }

        // BAND
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            val bandId = backStackEntry.arguments?.getString("bandId")
                ?: throw IllegalArgumentException("Band ID no proporcionado")

            val vm: BandVM = viewModel()

            BandScreen(
                bandId = bandId,
                vm = vm
            )
        }

        // USER INFO (perfil)
        composable(ObjRoutes.INFOUSER) {
            val vm: UserVM = viewModel()

            // 👇 Aquí ya no usamos "defaultUser"
            // Se asume que el usuario ya está en el VM tras login
            UserInfoScreen(
                userInfoId = vm.uiState.value.user?.id ?: "",
                vm = vm
            )
        }

        // SETTINGS
        composable(ObjRoutes.SETTINGS) {
            SettingsScreen(settingsVM)
        }

        // DIALOG
        dialog(ObjRoutes.DIALOG) {
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