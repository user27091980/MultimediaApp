package com.example.multimediaapp.navigation

// Permite crear funciones composables (UI declarativa)
// Permite obtener un ViewModel dentro de un Composable
// Controlador que gestiona la navegación y el back stack
// Contenedor principal de navegación en Compose
// Función que define una pantalla asociada a una ruta
// Importación de todas las pantallas (Screens)
// Importación de los ViewModels (lógica de negocio - MVVM)
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multimediaapp.view.pages.BandScreen
import com.example.multimediaapp.view.pages.LoginRegScreen
import com.example.multimediaapp.view.pages.LoginScreen
import com.example.multimediaapp.view.pages.MainScreen
import com.example.multimediaapp.view.pages.RegisterScreen
import com.example.multimediaapp.view.pages.SettingsScreen
import com.example.multimediaapp.view.pages.UserInfoScreen
import com.example.multimediaapp.viewmodel.uistate.LoginUiState
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
            val loginUiState by vm.uiState.collectAsState()

            LoginScreen(
                navController = navController,
                // Estado de la UI que vive en el ViewModel
                uiState = loginUiState,
                // Referencias a funciones del ViewModel
                onEmailChange = vm::onEmailChange,
                onPasswordChange = vm::onPasswordChange,
                onTogglePassword = vm::togglePasswordVisibility,
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
            val vm: BandVM = viewModel()
            BandScreen(
                bandId = "Default",
                vm = vm
            )
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

/**
 * Teoría:
 *NavHost es el contenedor de navegación en Jetpack Compose.
 * Su trabajo es:
 *
 * Decidir qué pantalla (Composable) se muestra según la ruta actual
 *
 * navController
 * val navController = rememberNavController()
 * Es el cerebro de la navegación
 * Guarda:
 * Pantalla actualHistorial (back stack)
 * Se usa para navegar

 * startDestination:
 * Es la primera pantalla que se muestra al abrir la app
 *
 * El bloque {} de NavHost
 *Aquí defines todas las rutas posibles:
 *{
 *  composable<SplashRoute> { ... }
 *
 *   composable<LoginRoute> { ... }
 *}
 *Cada composable es:

 *Una pantalla asociada a una ruta
 *¿Qué hace?
 *composable<SplashRoute> {
 *SplashScreen(navController)
 *}
 *Esto significa:
 *Cuando la ruta actual es SplashRoute
 *NavHost muestra:
 *SplashScreen
 * ¿Cómo funciona la navegación en tiempo real?
 * Ejemplo real: Splash → Login
 *
 * Desde SplashScreen haces:
 *
 * navController.navigate(LoginRegRoute)
 *
 * Lo que pasa internamente:
 *
 * navController cambia la ruta actual
 *
 * NavHost detecta el cambio
 *
 * Busca esta definición:
 *
 * composable<LoginRegRoute> {
 *     LoginRegScreen(navController)
 * }
 *
 *
 * Muestra LoginRegScreen
 *
 * La pantalla anterior queda en el back stack
 * Back Stack (botón atrás)
 *
 * Cuando navegas:
 *
 * navController.navigate(MainScreenRoute)
 *
 *
 * Se guarda esto:
 *
 * Splash → Login → MainScreen
 *
 *
 * Si llamas:
 *
 * navController.popBackStack()
 *
 *
 * Vuelves a:
 * Login
 *
 * Rutas tipadas vs rutas String
 * Estamos empleando Rutas tipadas
 * composable<MainScreenRoute> { ... }
 *
 *
 * Ventajas:
 *
 * Más seguras
 *
 * Menos errores de escritura
 *
 * Mejor escalabilidad
 *
 * Rutas String (las del BottomBar)
 * composable("home") { ... }
 *
 *
 * Normalmente se usan cuando:
 *
 * BottomNavigation
 *
 * Rutas simples
 *
 * No necesitas argumentos complejos
 *
 * Conviene unificarlas no usar ambas.
 * ¿Cómo encaja con Scaffold?
 *
 * Tu estructura real es:
 *
 * MainActivity
 *  └── Scaffold
 *      ├── TopBar
 *      ├── BottomBar
 *      └── NavHost  (aquí cambia el contenido)
 *
 *
 * TopBar y BottomBar NO cambian
 *
 * Solo cambia lo que hay dentro del NavHost
 *
 * Esto es ideal para apps con navegación fija.
 *
 * Esquema visual rápido
 * NavHost
 *  ├── SplashRoute → SplashScreen
 *  ├── LoginRoute → LoginScreen
 *  ├── MainScreenRoute → MainScreen
 *  ├── BandRoute → Band
 *  ├── SettingsRoute → Settings
 *
 *  Errores comunes:
 *
 *  Olvidar pasar navController a la pantalla
 *  Tener dos rutas distintas para la misma pantalla
 *  No limpiar el back stack al hacer login
 *
 * Términos:
 * NavHost = mapa de navegación
 * navController = controla el movimiento
 * startDestination = pantalla inicial
 * composable{} = una pantalla por ruta
 * Cambia la ruta → cambia la pantalla
 */
