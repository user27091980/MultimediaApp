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
import com.example.multimediaapp.ui.viewmodel.BandVM
import com.example.multimediaapp.view.pages.*
import com.example.multimediaapp.viewmodel.vm.*

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
            val vm: LoginRegVM = viewModel() // Si no necesita parámetros
            LoginRegScreen(navController = navController, vm = vm)
        }

        // Pantalla de login
        composable(ObjRoutes.LOGIN) {
            // Instanciamos dependencias
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val repository = remember { LoginRepo() }

            // Llamamos a la pantalla sin pasar vm, porque se crea dentro
            LoginScreen(
                navController = navController,
                dataStore = dataStore,
                repository = repository
            )
        }

        // Pantalla de registro
        composable(ObjRoutes.REGISTER) {
            val vm: RegisterVM = viewModel() // Si no necesita parámetros
            RegisterScreen(navController = navController, vm = vm)
        }

        // Pantalla principal
        composable(ObjRoutes.MAIN) {
            val vm: MainVM = viewModel() // Si no necesita parámetros
            MainScreen(navController = navController, viewModel = vm)
        }

        // Pantalla de banda con parámetro
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