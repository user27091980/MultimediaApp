package com.example.multimediaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.data.repository.LoginRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.ui.viewmodel.BandVM
import com.example.multimediaapp.view.pages.*
import com.example.multimediaapp.viewmodel.vm.*

/**
 * NavGraph:
 *
 * Define la navegación de la aplicación usando Jetpack Compose Navigation.
 * Aquí se registran todas las rutas y pantallas de la app.
 *
 * @param navController Controlador de navegación de Compose.
 * @param settingsVM ViewModel compartido para la pantalla de configuración.
 *
 * FUNCIONALIDAD:
 * - Gestiona rutas principales y secundarias
 * - Crea ViewModels específicos por pantalla
 * - Inyecta dependencias necesarias (Repositories, DataStore)
 * - Soporta pantallas con parámetros y diálogos
 *
 * ESTRUCTURA DE NAVEGACIÓN:
 *
 * 1. LOGINREG       -> Pantalla inicial: Login / Register
 * 2. LOGIN          -> Pantalla de login de usuario
 * 3. REGISTER       -> Pantalla de registro
 * 4. MAIN           -> Pantalla principal de la app
 * 5. BAND/{bandId}  -> Pantalla de detalles de banda con parámetro
 * 6. INFOUSER       -> Información del usuario
 * 7. SETTINGS       -> Configuración de la app
 * 8. DIALOG         -> Diálogo de registro
 *
 * NOTAS:
 * - Se usan `remember` y `viewModel()` para mantener estado y ViewModels.
 * - Los parámetros de ruta (como bandId) se obtienen de backStackEntry.
 * - Los diálogos se manejan con `dialog` de Compose Navigation.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    settingsVM: SettingsVM
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = ObjRoutes.LOGINREG
    ) {

        // Pantalla inicial Login / Register
        composable(ObjRoutes.LOGINREG) {
            val vm: LoginRegVM = viewModel() // ViewModel sin parámetros
            LoginRegScreen(navController = navController, vm = vm)
        }

        // Pantalla de login
        composable(ObjRoutes.LOGIN) {
            // Usamos el context del LocalContext
            val dataStore = DataStoreManager(LocalContext.current)
            val repository = LoginRepo(RetrofitModule.loginApi)

            // El ViewModel se encarga de mantener estas instancias vivas
            val loginViewModel: LoginVM = viewModel(
                factory = LoginVMFactory(dataStore, repository)
            )

            LoginScreen(
                navController = navController,
                loginVM = loginViewModel
            )
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

        // Pantalla de detalles de banda con parámetro bandId
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            val bandId = backStackEntry.arguments?.getString("bandId")
                ?: throw IllegalArgumentException("Band ID no proporcionado")
            val vm: BandVM = viewModel()
            BandScreen(bandId = bandId, vm = vm)
        }

        // Información del usuario
        composable(ObjRoutes.INFOUSER) {
            val vm: UserInfoVM = viewModel()
            UserInfoScreen(userInfoId = "defaultUser", vm = vm)
        }

        // Pantalla de configuración
        composable(ObjRoutes.SETTINGS) {
            SettingsScreen(settingsVM)
        }

        // Diálogo de registro
        dialog(ObjRoutes.DIALOG) {
            DialogRegisterScreen(navController = navController)
        }
    }
}