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
 * Pantalla de registro de usuario.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado de la pantalla de registro.
 */
@Composable
fun RegisterScreen(
    navController: NavController,
    // obtiene automáticamente el ViewModel asociado al ciclo de vida
    vm: RegisterVM = viewModel()
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
            ), contentAlignment = Alignment.Center

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
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
            ) {
                TextFieldsComponent(// Pasamos los valores y callbacks al ViewModel
                    email = uiState.email,
                    pass = uiState.pass,
                    country = uiState.country,
                    name = uiState.name,
                    lastName = uiState.lastName,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPassChange,
                    onCountryChange = vm::onCountryChange,
                    onNameChange = vm::onNameChange,
                    onLastNameChange = vm::onLastNameChange
                )
                // Mostramos error si existe
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error, color = Color.Red, modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            // Botón "Aceptar" que navega al dialog de confirmación
            ButtonAccept(

                onClick = {
                    vm.validateFields {
                        // Navegar al diálogo  si el registro fue exitoso
                        navController.navigate(ObjRoutes.LOGIN)
                    }
                })

            // Botón "Cancelar" que regresa a la pantalla de login y registro
            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGINREG) })
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
/*
 * Pantalla de registro de usuario.
 *
 * OBJETIVO:
 *
 * - Permitir al usuario crear una cuenta nueva.
 * - Recoger datos como:
 *   - Email
 *   - Contraseña
 *   - País
 *   - Nombre
 *   - Apellido
 * - Validar los datos antes de continuar.
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - RegisterVM gestiona la lógica y el estado.
 * - RegisterScreen solo se encarga de la UI.
 *
 * FLUJO DE DATOS:
 *
 * 1. Se observa el estado del ViewModel:
 *      val uiState by vm.uiState.collectAsState()
 *
 * 2. Los campos del formulario se enlazan al ViewModel:
 *      onEmailChange = vm::onEmailChange
 *
 * 3. Cuando el usuario pulsa "Aceptar":
 *      - Se validan los campos con:
 *          vm.validateFields { ... }
 *      - Si todo es correcto, se navega al diálogo de confirmación.
 *
 * ESTRUCTURA DE LA UI:
 *
 * 1. Box:
 *    - Contenedor principal.
 *    - Ocupa toda la pantalla.
 *    - Aplica fondo con MaterialTheme.
 *
 * 2. Column principal:
 *    - Centra los elementos.
 *    - Aplica padding para separar del borde.
 *
 * 3. Subcolumn:
 *    - Contiene los campos de texto.
 *    - Usa weight(1f) para ocupar el espacio disponible.
 *
 * COMPONENTES:
 *
 * TextFieldsComponent:
 * - Agrupa todos los campos del formulario.
 * - Recibe:
 *   - valores actuales (estado)
 *   - callbacks para actualizar el estado
 *
 * ButtonAccept:
 * - Ejecuta la validación del formulario.
 * - Si es correcto → navega a:
 *      ObjRoutes.DIALOG
 *
 * ButtonCancel:
 * - Cancela el registro.
 * - Vuelve a:
 *      ObjRoutes.LOGINREG
 *
 * MANEJO DE ERRORES:
 *
 * uiState.errorMessage:
 * - Si existe, se muestra en rojo debajo del formulario.
 *
 * NAVEGACIÓN:
 *
 * navController.navigate(route)
 * - Permite cambiar de pantalla dentro de la app.
 *
 * PREVIEW:
 *
 * RegisterScreenPreview:
 * - Permite ver la UI sin ejecutar la app.
 * - Usa rememberNavController() como controlador simulado.
 *
 * BENEFICIOS:
 *
 * - Separación clara entre UI y lógica.
 * - Reutilización de componentes.
 * - Validación centralizada en el ViewModel.
 * - UI reactiva gracias a Compose.
 *
 * NOTAS IMPORTANTES:
 *
 * - collectAsState():
 *   Convierte el estado del ViewModel en observable.
 *
 * - viewModel():
 *   Obtiene la instancia del ViewModel ligada al ciclo de vida.
 *
 * - LaunchedEffect:
 *   (no usado aquí, pero típico en pantallas similares)
 *
 * - La validación se ejecuta antes de permitir la navegación.
 */

