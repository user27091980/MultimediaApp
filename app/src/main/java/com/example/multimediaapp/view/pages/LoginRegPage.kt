package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.view.components.ButtonLogin
import com.example.multimediaapp.view.components.ButtonRegister
import com.example.multimediaapp.viewmodel.vm.LoginRegVM

/**
 * Pantalla inicial que ofrece opciones de Login o Registro.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado de la pantalla Login/Registro.
 */
@Composable
fun LoginRegScreen(
    navController: NavController,
    vm: LoginRegVM = viewModel()// obtiene automáticamente el ViewModel asociado al ciclo de vida
) {
    // Caja principal que ocupa toda la pantalla
    // Se aplica un fondo degradado de oscuro a negro
    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(

                MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        // Columna que contiene los botones de login y registro
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(horizontal = 32.dp)

        ) {
            //boton de Login
            ButtonLogin(
                onClick = { navController.navigate(ObjRoutes.LOGIN) }
            )
            // Botón Registro
            ButtonRegister(

                onClick = { navController.navigate(ObjRoutes.REGISTER) }

            )
        }
    }
}

/**
 * Preview para mostrar cómo se ve la pantalla en Android Studio sin ejecutar la app.
 */
@Preview()
@Composable
fun LoginRegScreenPreview() {

    val navController = rememberNavController()

    LoginRegScreen(
        navController = navController,
        vm = LoginRegVM() //solo si tu VM tiene constructor vacío
    )
}

/*
 * Este archivo define la pantalla inicial de la aplicación,
 * donde el usuario puede elegir entre iniciar sesión o registrarse.
 *
 * OBJETIVO:
 *
 * - Ofrecer dos opciones principales:
 *   - Login
 *   - Registro
 *
 * - Es la pantalla de entrada al flujo de autenticación.
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - La lógica se gestiona en LoginRegVM.
 * - La UI solo muestra botones y responde a eventos.
 *
 * UI PRINCIPAL:
 *
 * 1. Box:
 *    - Contenedor principal de toda la pantalla.
 *    - Permite centrar el contenido.
 *    - Aplica fondo usando MaterialTheme.
 *
 * 2. Column:
 *    - Organiza los botones de forma vertical.
 *    - Centra los elementos horizontalmente.
 *    - Añade separación entre botones con:
 *        Arrangement.spacedBy(20.dp)
 *
 * COMPONENTES:
 *
 * ButtonLogin:
 * - Navega a la pantalla de login.
 *
 * ButtonRegister:
 * - Navega a la pantalla de registro.
 *
 * NAVEGACIÓN:
 *
 * NavController:
 * - Permite cambiar de pantalla dentro de la app.
 * - Se usa con rutas definidas en ObjRoutes:
 *   - LOGIN
 *   - REGISTER
 *
 * ESTADO:
 *
 * vm: LoginRegVM
 * - Se obtiene mediante viewModel().
 * - Actualmente no gestiona estado visible en esta pantalla,
 *   pero se mantiene para futuras ampliaciones.
 *
 * PREVIEW:
 *
 * LoginRegScreenPreview:
 * - Permite visualizar la UI sin ejecutar la app.
 * - Usa rememberNavController() como controlador simulado.
 *
 * DISEÑO:
 *
 * - Fondo con MaterialTheme.colorScheme.background.
 * - Contenido centrado en pantalla.
 * - Uso de padding horizontal para mejorar estética.
 *
 * BENEFICIOS:
 *
 * - Pantalla simple y clara.
 * - Fácil navegación.
 * - Código reutilizable y mantenible.
 * - Separación de UI y lógica.
 *
 * NOTA:
 *
 * - Esta pantalla actúa como punto de entrada de la autenticación.
 * - No contiene lógica compleja, solo navegación.
 */