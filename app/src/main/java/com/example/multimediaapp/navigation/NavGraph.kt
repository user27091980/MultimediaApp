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
            val uiState by vm.uiState.collectAsState()

            LoginScreen(
                navController = navController,
                vm = vm
            )
        }

        // Pantalla de registro
        composable(ObjRoutes.REGISTER) {
            val vm: RegisterVM = viewModel()
            RegisterScreen(navController = navController, vm = vm)
        }

        // Pantalla principal
        composable(ObjRoutes.MAIN) {
            val api = MultimediaApiService.create()
            val vm: MainVM = viewModel(factory = MainVMFactory(api))

            MainScreen(navController = navController, vm = vm)
        }

        // Pantalla de bandas con parámetro
        composable("${ObjRoutes.BAND}/{bandId}") { backStackEntry ->
            val bandId = backStackEntry.arguments?.getString("bandId") ?: "Default"
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
