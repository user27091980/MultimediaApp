package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.example.multimediaapp.view.components.TextFieldsLoginComponent
import com.example.multimediaapp.viewmodel.vm.LoginVM


/**
 * Pantalla de login.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado y la lógica del login.
 */
@Composable
fun LoginScreen(
    navController: NavController,
    // obtiene automáticamente el ViewModel asociado al ciclo de vida
    vm: LoginVM = viewModel()
) {
    // Obtenemos el estado actual del ViewModel
    val uiState by vm.uiState.collectAsState()

    // Caja principal que ocupa toda la pantalla
    // Se aplica un fondo degradado vertical de gris oscuro a negro
    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        // Columna principal que contiene los campos de texto y botones
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
                TextFieldsLoginComponent(// Pasamos los valores y callbacks al ViewModel
                    email = uiState.email,
                    pass = uiState.password,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPasswordChange
                )
                // Mostramos error si existe
                uiState.errorMessage?.let { error ->
                    androidx.compose.material3.Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            // Botón "Aceptar" que navega al dialog de confirmación
            ButtonAccept(
                onClick = {
                    if (vm.validateFieldsLogin()) {
                        // Si todo es correcto, navega al dialog
                        navController.navigate(ObjRoutes.MAIN)
                    }
                }
            )
            // Botón "Cancelar" que regresa a la pantalla de login
            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGINREG) }
            )
        }
    }
}

/**
 * Preview para mostrar la pantalla de registro en Android Studio sin ejecutar la app.
 */
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


/*
 * Este archivo define la pantalla de login de la aplicación utilizando Jetpack Compose.
 *
 * OBJETIVO:
 *
 * - Permitir al usuario iniciar sesión.
 * - Validar los campos (email y contraseña).
 * - Navegar a otras pantallas según la acción del usuario.
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - La lógica está en LoginVM.
 * - La UI se encarga solo de mostrar datos y reaccionar a eventos.
 *
 * ESTADO:
 *
 * uiState:
 * - Se obtiene del ViewModel mediante collectAsState().
 * - Contiene:
 *   - email
 *   - password
 *   - errorMessage
 *
 * INTERACCIÓN CON VIEWMODEL:
 *
 * - onEmailChange → vm::onEmailChange
 * - onPassChange → vm::onPasswordChange
 *
 * Esto permite actualizar el estado en tiempo real.
 *
 * UI PRINCIPAL:
 *
 * 1. Box:
 *    - Contenedor principal.
 *    - Centra el contenido en pantalla.
 *    - Aplica fondo usando MaterialTheme.
 *
 * 2. Column:
 *    - Organiza los elementos verticalmente.
 *    - Añade padding para separación visual.
 *
 * 3. TextFieldsLoginComponent:
 *    - Muestra los campos de email y contraseña.
 *
 * VALIDACIÓN:
 *
 * - Se realiza al pulsar el botón "Aceptar".
 * - Se llama a:
 *      vm.validateFieldsLogin()
 *
 * - Si los datos son correctos:
 *      → Navega a la pantalla MAIN.
 * - Si no:
 *      → Muestra mensaje de error.
 *
 * MENSAJE DE ERROR:
 *
 * - Se muestra si uiState.errorMessage != null.
 * - Se renderiza en color rojo.
 *
 * BOTONES:
 *
 * ButtonAccept:
 * - Ejecuta la validación.
 * - Navega a MAIN si todo es correcto.
 *
 * ButtonCancel:
 * - Navega a LOGINREG (volver o cancelar).
 *
 * NAVEGACIÓN:
 *
 * NavController:
 * - Gestiona el flujo entre pantallas.
 * - Se usa con rutas definidas en ObjRoutes.
 *
 * PREVIEW:
 *
 * LoginScreenPreview:
 * - Permite visualizar la pantalla sin ejecutar la app.
 * - Usa rememberNavController() como controlador simulado.
 *
 * DISEÑO:
 *
 * - Fondo con MaterialTheme.colorScheme.background.
 * - Contenido centrado.
 * - Uso de paddings para separación.
 *
 * BENEFICIOS:
 *
 * - Código limpio y modular.
 * - Separación de UI y lógica.
 * - UI reactiva basada en estado.
 * - Fácil de mantener y escalar.
 *
 * NOTA:
 *
 * - La lógica de autenticación no está aquí, sino en el ViewModel.
 * - Esta pantalla es puramente declarativa.
 */

