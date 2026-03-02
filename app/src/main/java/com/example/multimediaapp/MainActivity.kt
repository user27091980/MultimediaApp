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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.BottomBar
import com.example.multimediaapp.view.components.TopBar
import com.example.multimediaapp.view.pages.BandScreen
import com.example.multimediaapp.view.pages.LoginScreen
import com.example.multimediaapp.view.pages.MainScreen
import com.example.multimediaapp.view.pages.RegisterScreen
import com.example.multimediaapp.view.pages.SettingsScreen
import com.example.multimediaapp.view.pages.UserInfoScreen


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultimediaAppTheme {
                val navController = rememberNavController()
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    //personalized topbar
                    topBar = {
                        if (currentRoute !in listOf(

                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN
                            )
                        ) TopBar(navController)
                    },
                    //personalized bottombar
                    bottomBar = {
                        if (currentRoute in listOf(
                                ObjRoutes.MAIN,
                                ObjRoutes.BAND,
                                ObjRoutes.SETTINGS,
                                ObjRoutes.INFOUSER

                            )
                        ) {
                            BottomBar(navController)
                        }
                    },
                    //main content
                    content = { innerPadding ->
                        //box for wrap the content and apply padding
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            NavHost(
                                navController = navController,
                                starDestination = ObjRoutes.LOGINREG,
                            ) {
                                composable(ObjRoutes.LOGIN) {
                                    LoginScreen(
                                        uiState = TODO(),
                                        onEmailChange = TODO(),
                                        onPasswordChange = TODO(),
                                        onTogglePassword = TODO(),
                                        onLoginClick = TODO()
                                    )
                                }
                                composable(ObjRoutes.REGISTER) {
                                    RegisterScreen()
                                }
                                composable(ObjRoutes.MAIN) {
                                    MainScreen()
                                }
                                composable(ObjRoutes.BAND) {
                                    BandScreen()
                                }
                                composable(ObjRoutes.INFOUSER) {
                                    UserInfoScreen(
                                        userInfoId = TODO(),
                                        vm = TODO()
                                    )
                                }
                                composable(ObjRoutes.SETTINGS) {
                                    SettingsScreen()
                                }

                            }
                        }
                    }
                )
            }
        }
    }
}

/**
 * Notas apuntes:
 *NavHost es el contenedor de navegación en Jetpack Compose.
 * Su trabajo es:
 *
 * Decidir qué pantalla (Composable) se muestra según la ruta actual
 *
 * 1️⃣ navController
 * val navController = rememberNavController()
 * Es el cerebro de la navegación
 * Guarda:
 * Pantalla actualHistorial (back stack)
 * Se usa para navegar

 * 2️⃣ startDestination:
 * Es la primera pantalla que se muestra al abrir la app
 *
 * 3️⃣ El bloque {} de NavHost
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
 *👉 SplashScreen
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
 * 👉 Login
 *
 * Rutas tipadas vs rutas String
 * 🟢 Rutas tipadas (las que usas)
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
 * 🔵 Rutas String (las del BottomBar)
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
 * ⚠️ En tu app tienes ambas mezcladas, lo cual funciona, pero conviene unificarlas.
 * ¿Cómo encaja con Scaffold?
 *
 * Tu estructura real es:
 *
 * MainActivity
 *  └── Scaffold
 *      ├── TopBar
 *      ├── BottomBar
 *      └── NavHost  👈 aquí cambia el contenido
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
 *  Errores comunes (para que no caigas 😅)
 *
 * ❌ Olvidar pasar navController a la pantalla
 * ❌ Tener dos rutas distintas para la misma pantalla
 * ❌ No limpiar el back stack al hacer login
 *
 * Términos:
 * NavHost = mapa de navegación
 * navController = controla el movimiento
 * startDestination = pantalla inicial
 * composable{} = una pantalla por ruta
 * Cambia la ruta → cambia la pantalla
 */