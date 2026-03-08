package com.example.multimediaapp.view.pages

import com.example.multimediaapp.view.components.ButtonLogin
import com.example.multimediaapp.view.components.ButtonRegister
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
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
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black) // verde Spotify → negro
                )
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

/**
 * notas:
 *
 * - **Box**: Contenedor que permite posicionar elementos con alineación y superposición.
 * - **Column**: Organiza elementos de manera vertical.
 * - **horizontalAlignment / verticalArrangement**: Controlan alineación y separación de los hijos.
 * - **Modifier.padding / fillMaxSize / background**: Permiten ajustar tamaño, márgenes y fondo.
 * - **ButtonLogin / ButtonRegister**: Componentes reutilizables para acciones principales.
 * - **navController.navigate(route)**: Permite cambiar de pantalla dentro de la navegación de Compose.
 * - **@Preview**: Permite ver la UI en el IDE sin ejecutar la app.
 */