package com.example.multimediaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
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

        // Pantalla inicial Login / Register
        composable(ObjRoutes.LOGINREG) {
            LoginRegScreen(navController = navController)
        }

        // Pantalla de login
        composable(ObjRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        // Pantalla de registro
        composable(ObjRoutes.REGISTER) {
            RegisterScreen(navController = navController)
        }

        // Pantalla principal
        composable(ObjRoutes.MAIN) {
            MainScreen(navController = navController)
        }

        // Pantalla de banda con parámetro
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            val bandId = backStackEntry.arguments?.getString("bandId")
                ?: throw IllegalArgumentException("Band ID no proporcionado")
            BandScreen(bandId = bandId)
        }

        // Información del usuario
        composable(ObjRoutes.INFOUSER) {
            UserInfoScreen(userInfoId = "defaultUser")
        }

        composable(ObjRoutes.SETTINGS) {
            SettingsScreen(settingsVM)
        }

        dialog(ObjRoutes.DIALOG) {
            DialogRegisterScreen(navController = navController)
        }
    }
}
