package com.example.multimediaapp.navigation

// Permite crear funciones composables (UI declarativa)
import androidx.compose.runtime.Composable
// Permite obtener un ViewModel dentro de un Composable
import androidx.lifecycle.viewmodel.compose.viewModel
// Controlador que gestiona la navegación y el back stack
import androidx.navigation.NavHostController
// Contenedor principal de navegación en Compose
import androidx.navigation.compose.NavHost
// Función que define una pantalla asociada a una ruta
import androidx.navigation.compose.composable
// Importación de todas las pantallas (Screens)
import com.example.multimediaapp.view.pages.BandScreen
import com.example.multimediaapp.view.pages.LoginRegScreen
import com.example.multimediaapp.view.pages.LoginScreen
import com.example.multimediaapp.view.pages.MainScreen
import com.example.multimediaapp.view.pages.RegisterScreen
import com.example.multimediaapp.view.pages.SettingsScreen
import com.example.multimediaapp.view.pages.UserInfoScreen
// Importación de los ViewModels (lógica de negocio - MVVM)
import com.example.multimediaapp.viewmodel.vm.BandVM
import com.example.multimediaapp.viewmodel.vm.LoginRegVM
import com.example.multimediaapp.viewmodel.vm.LoginVM
import com.example.multimediaapp.viewmodel.vm.RegisterVM
import com.example.multimediaapp.viewmodel.vm.UserInfoVM

// Función principal que define todas las rutas de la aplicación
@Composable
fun NavGraph(navController: NavHostController) {
    // NavHost es el contenedor que escucha la ruta actual
    // y muestra la pantalla correspondiente
    NavHost(
        navController = navController,// Controlador de navegación
        startDestination = ObjRoutes.LOGINREG// Pantalla inicial de la app
    ) {
       //ruta login y registro
        composable(ObjRoutes.LOGINREG) {
            // Se obtiene el ViewModel asociado a esta pantalla
            val vm: LoginRegVM = viewModel()
            // Se muestra la pantalla
            LoginRegScreen(
                // Permite navegar desde la pantalla
                navController = navController,
                // Lógica asociada
                vm = vm
            )
        }
        //ruta login
        composable(ObjRoutes.LOGIN) {
            // ViewModel del login
            val vm: LoginVM = viewModel()

            LoginScreen(
                navController = navController,
                // Estado de la UI que vive en el ViewModel
                uiState = vm.uiState,
                // Referencias a funciones del ViewModel
                onEmailChange = vm::onEmailChange,
                onPasswordChange = vm::onPasswordChange,
                onTogglePassword = vm::onTogglePassword,
                // Acción cuando el usuario hace login
                onLoginClick = {
                    // Ejecuta lógica de autenticación
                    vm.login()
                    //navegación a main
                    navController.navigate(ObjRoutes.MAIN) {
                        // Limpia el back stack hasta LOGINREG
                        // Esto evita volver atrás al login
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                    }
                }
            )
        }
        //ruta registro
        composable(ObjRoutes.REGISTER) {
            val vm: RegisterVM = viewModel()
            RegisterScreen(
                navController = navController,
                vm = vm
            )
        }
        //ruta pantalla principal
        composable(ObjRoutes.MAIN) {
            //pasamos el viewModel de las bandas ya que queremos que las muestre(view model asociado)
            val vm: BandVM = viewModel()

            MainScreen(
                navController = navController,
                vm = vm
            )
        }
        //ruta bandas
        composable(ObjRoutes.BAND) {
            BandScreen()
        }

        //ruta información de usuario
        composable(ObjRoutes.INFOUSER) {
            val vm: UserInfoVM = viewModel()
            // ID temporal (más adelante debería pasarse por argumentos)
            val userId = "defaultUser"
            UserInfoScreen(
                userInfoId = userId,
                vm = vm
            )
        }
        //ruta configuración
        composable(ObjRoutes.SETTINGS) {
            SettingsScreen()
        }
    }
}
