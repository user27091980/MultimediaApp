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
 * LoginRegScreen:
 *
 * Pantalla inicial de la aplicación que ofrece al usuario dos opciones principales:
 * - Iniciar sesión (Login)
 * - Registrarse (Register)
 *
 * Utiliza Jetpack Compose para la construcción de la interfaz y Navigation para moverse
 * entre pantallas.
 *
 * @param navController Controlador de navegación que permite ir a otras pantallas.
 * @param vm ViewModel que maneja el estado y la lógica de la pantalla Login/Registro.
 */
@Composable
fun LoginRegScreen(
    navController: NavController,
    vm: LoginRegVM = viewModel() // Obtiene automáticamente el ViewModel asociado al ciclo de vida
) {
    // Contenedor principal que ocupa toda la pantalla
    // Se centra el contenido y se aplica un fondo
    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background // fondo basado en el tema actual
            ),
        contentAlignment = Alignment.Center // centra los hijos dentro del Box
    ) {
        // Columna que contiene los botones de acción
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // centra horizontalmente
            verticalArrangement = Arrangement.spacedBy(20.dp), // espacio entre botones
            modifier = Modifier
                .padding(horizontal = 32.dp) // padding lateral
        ) {
            // Botón para navegar a la pantalla de Login
            ButtonLogin(
                onClick = { navController.navigate(ObjRoutes.LOGIN) }
            )

            // Botón para navegar a la pantalla de Registro
            ButtonRegister(
                onClick = { navController.navigate(ObjRoutes.REGISTER) }
            )
        }
    }
}

/**
 * Vista previa de la pantalla LoginRegScreen.
 *
 * Permite ver cómo se renderiza la UI en Android Studio sin ejecutar la aplicación.
 */
@Preview()
@Composable
fun LoginRegScreenPreview() {
    val navController = rememberNavController()

    LoginRegScreen(
        navController = navController,
        vm = LoginRegVM() // Solo si el ViewModel tiene constructor vacío
    )
}

/**
 * Notas explicativas:
 *
 * 1. **Box**: Contenedor que permite posicionar elementos con alineación y superposición.
 * 2. **Column**: Organiza elementos de manera vertical.
 * 3. **horizontalAlignment / verticalArrangement**: Controlan alineación y separación de los hijos.
 * 4. **Modifier.padding / fillMaxSize / background**: Ajustan tamaño, márgenes y fondo.
 * 5. **ButtonLogin / ButtonRegister**: Componentes reutilizables que ejecutan navegación.
 * 6. **navController.navigate(route)**: Permite cambiar de pantalla dentro del sistema de navegación de Compose.
 * 7. **@Preview**: Permite visualizar la UI en el IDE sin ejecutar la app.
 */