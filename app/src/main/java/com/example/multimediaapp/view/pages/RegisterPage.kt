package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.multimediaapp.viewmodel.vm.RegisterVM

/**
 * RegisterScreen:
 *
 * Pantalla de registro de usuario en la aplicación.
 *
 * Funcionalidades principales:
 * - Muestra campos de texto para email, usuario, contraseña, país, nombre y apellido.
 * - Permite ingresar datos que se guardan en el estado del ViewModel.
 * - Valida los campos antes de navegar al diálogo de confirmación.
 * - Permite cancelar y volver a la pantalla de login/registro.
 *
 * @param navController Controlador de navegación para cambiar de pantalla.
 * @param vm ViewModel que maneja el estado de la pantalla de registro.
 */
@Composable
fun RegisterScreen(
    navController: NavController,
    vm: RegisterVM = viewModel() // Obtiene automáticamente el ViewModel asociado al ciclo de vida
) {
    // Estado observable del ViewModel
    val uiState by vm.uiState.collectAsState()

    // Caja principal que ocupa toda la pantalla con fondo del tema
    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        // Columna principal que contiene campos de texto y botones
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Subcolumna para los campos de texto
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                // Componente que agrupa todos los TextFields
                TextFieldsComponent(
                    email = uiState.email,
                    user = uiState.user,
                    pass = uiState.pass,
                    country = uiState.country,
                    name = uiState.name,
                    lastName = uiState.lastName,
                    onUserChange = vm::onUserChange,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPassChange,
                    onCountryChange = vm::onCountryChange,
                    onNameChange = vm::onNameChange,
                    onLastNameChange = vm::onLastNameChange
                )

                // Mostrar mensaje de error si existe
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Botón "Aceptar" que valida los campos y navega al diálogo
            ButtonAccept(
                onClick = {
                    vm.validateFields {
                        // Navega a DialogRegisterScreen si los campos son válidos
                        navController.navigate(ObjRoutes.DIALOG)
                    }
                }
            )

            // Botón "Cancelar" que regresa a LoginRegScreen
            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGINREG) }
            )
        }
    }
}

/**
 * Vista previa de la pantalla de registro.
 *
 * Permite visualizar la UI en Android Studio sin ejecutar la app.
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
 * Notas / teoría:
 *
 * 1. **Box**: Contenedor que permite centrar y superponer elementos en la pantalla.
 * 2. **Column**: Organiza los elementos hijos verticalmente.
 * 3. **Modifier.fillMaxSize() / fillMaxWidth()**: Hace que la UI ocupe todo el espacio disponible.
 * 4. **Modifier.padding()**: Aplica márgenes internos para separar elementos de los bordes.
 * 5. **TextFieldsComponent**: Componente que agrupa todos los campos de entrada de usuario.
 * 6. **ButtonAccept / ButtonCancel**: Botones reutilizables para confirmar o cancelar la acción.
 * 7. **navController.navigate(route)**: Permite cambiar de pantalla usando la navegación de Compose.
 * 8. **collectAsState()**: Convierte un StateFlow del ViewModel en un estado observable por Compose.
 * 9. **@Preview**: Permite previsualizar la UI en el IDE sin ejecutar la app.
 *
 * Nota adicional:
 * - Se conecta con RegisterVM para validar campos y mostrar errores antes de navegar.
 */