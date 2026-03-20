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
import com.example.multimediaapp.view.components.TextFieldsComponent
import com.example.multimediaapp.viewmodel.vm.UserVM

/**
 * Pantalla de registro de usuario.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado de la pantalla de registro.
 */

@Composable
fun RegisterScreen(
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

                TextFieldsComponent(
                    user = "", // ya no usas user separado
                    email = uiState.email,
                    pass = uiState.pass,
                    country = uiState.country,
                    name = uiState.name,
                    lastName = uiState.lastName,
                    onUserChange = {}, // no necesario
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPassChange,
                    onCountryChange = vm::onCountryChange,
                    onNameChange = vm::onNameChange,
                    onLastNameChange = vm::onLastNameChange
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
                    vm.register {
                        // Navegar al login o main tras registro exitoso
                        navController.navigate(ObjRoutes.LOGIN) {
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

/**
 * Preview para mostrar la pantalla de registro en Android Studio sin ejecutar la app.
 */
@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()

    RegisterScreen(
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

