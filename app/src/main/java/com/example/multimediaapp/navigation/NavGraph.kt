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

        // Pantalla inicial Login / Register
        composable(ObjRoutes.LOGINREG) {
            val vm: LoginRegVM = viewModel()
            // CORRECCIÓN: el parámetro era 'viewModel', no 'v'
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
            val vm: MainVM = viewModel()
            MainScreen(navController = navController, viewModel = vm)
        }

        // Pantalla de banda con parámetro
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            //val bandId = backStackEntry.arguments?.getString("bandId") ?: ""
            val bandId = backStackEntry.arguments?.getString("bandId")
                ?: throw IllegalArgumentException("Band ID no proporcionado")
            val vm: BandVM = viewModel()
            // Pasamos el ID y el VM a la pantalla de detalle
            BandScreen(bandId = bandId, vm = vm)
        }

        // Información del usuario
        composable(ObjRoutes.INFOUSER) {
            val vm: UserInfoVM = viewModel()
            // Aquí podrías obtener el userId real de un Preference o del LoginVM
            UserInfoScreen(userInfoId = "defaultUser", vm = vm)
        }

        composable(ObjRoutes.SETTINGS) {
            SettingsScreen(settingsVM)
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

/*
 * Este archivo define el grafo de navegación (NavGraph) de la aplicación.
 *
 * La función NavGraph es un Composable que centraliza toda la navegación
 * entre pantallas usando Jetpack Compose Navigation.
 *
 * NavHostController:
 * - Es el controlador principal de navegación.
 * - Gestiona las rutas (screens) y el historial de navegación.
 *
 * NavHost:
 * - Define el punto de entrada de la navegación.
 * - Establece la pantalla inicial (startDestination).
 * - Contiene todas las rutas disponibles en la aplicación.
 *
 * composable(route):
 * - Define una pantalla dentro del sistema de navegación.
 * - Cada ruta está asociada a un Composable (pantalla).
 * - Se pueden inyectar ViewModels usando viewModel().
 *
 * Rutas utilizadas:
 * - LOGINREG: pantalla inicial de login/registro
 * - LOGIN: pantalla de inicio de sesión
 * - REGISTER: pantalla de registro de usuario
 * - MAIN: pantalla principal de la app
 * - BAND/{bandId}: pantalla de detalle de banda con parámetro dinámico
 * - INFOUSER: información del usuario
 * - SETTINGS: configuración de la app
 * - DIALOG: pantalla tipo diálogo
 * - ADDBAND: pantalla para crear una nueva banda
 *
 * ViewModel:
 * - Se crean dentro de cada pantalla usando viewModel().
 * - Se encargan de la lógica de negocio y gestión de estado.
 * - Están ligados al ciclo de vida de la pantalla.
 *
 * Rutas con parámetros:
 * - Se utilizan para pasar datos entre pantallas.
 * - Ejemplo: "BAND/{bandId}" permite recibir el ID de una banda.
 *
 * dialog():
 * - Permite mostrar pantallas como diálogos modales.
 *
 * Flujo de navegación:
 * - El NavController se pasa a las pantallas para permitir navegación
 *   (por ejemplo, ir atrás o cambiar de pantalla).
 *
 * Este archivo centraliza toda la navegación, facilitando el mantenimiento,
 * escalabilidad y organización de la aplicación.
 */