package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.view.components.ButtonAccept
import com.example.multimediaapp.view.components.ButtonCancel
import com.example.multimediaapp.view.components.TextFieldsLoginComponent
import com.example.multimediaapp.viewmodel.vm.UserVM


/**
 * Pantalla de login.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado y la lógica del login.
 */
@Composable
fun LoginScreen(
    navController: NavController,
    vm: UserVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                TextFieldsLoginComponent(
                    email = uiState.email,
                    pass = uiState.pass,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPassChange
                )

                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
            }

            ButtonAccept(
                onClick = {
                    vm.login {
                        navController.navigate(ObjRoutes.MAIN) {
                            popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                        }
                    }
                }
            )

            ButtonCancel(
                onClick = {
                    navController.navigate(ObjRoutes.LOGINREG)
                }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()

    LoginScreen(
        navController = navController
    )
}
/**
 * notas:
 *
 * - Box: Contenedor que permite centrar y superponer elementos.
 * - Column: Organiza los hijos verticalmente.
 * - Modifier.fillMaxSize(): La UI ocupa todo el espacio disponible.
 * - Modifier.padding(): Aplica márgenes internos para separar los elementos de los bordes.
 * - TextFieldsComponent(): Contiene los campos de entrada de usuario, email y contraseña.
 * - ButtonAcept / ButtonCancel: Botones reutilizables para aceptar o cancelar la acción.
 * - navController.navigate(route): Navega a la ruta definida dentro de la navegación de Compose.
 * - @Preview: Permite previsualizar la UI en el IDE sin ejecutar la app.
 *
 * Nota: Se puede conectar con el ViewModel para validar campos antes de navegar.
 */


/**
 * NOTAS
 *
 * - OutlinedTextField: Campo de texto con borde, permite customizar colores y visibilidad de la contraseña.
 * - TextFieldDefaults.outlinedTextFieldColors: Personaliza colores de texto, borde, cursor y label.
 * - LaunchedEffect(Unit): Ejecuta código solo una vez al entrar en composición; aquí escucha eventos del ViewModel.
 * - collectAsState(): Convierte un StateFlow en un estado observable en Compose.
 * - Row / Column: Organiza elementos horizontal o verticalmente.
 * - Spacer: Añade espacio entre elementos.
 * - ButtonAcept / ButtonCancel: Componentes reutilizables para acciones principales y secundarias.
 */

