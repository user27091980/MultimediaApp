package com.example.multimediaapp.view.pages

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.R
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.viewmodel.vm.DialogVM

/**
 * Composable que representa un diálogo de confirmación para el registro de un usuario.
 *
 * Muestra un AlertDialog con botones de "sí" y "no".
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm Instancia del ViewModel encargado de manejar el estado del diálogo.
 */

@Composable
fun DialogRegisterScreen(
    navController: NavController,
    vm: DialogVM = viewModel(),

    ) {
    // Observa el estado del diálogo desde el ViewModel
    val uiState by vm.uiState.collectAsState()
    // AlertDialog es un componente de Compose para mostrar diálogos modales
    AlertDialog(
        // Acción al intentar cerrar el diálogo tocando fuera de él
        onDismissRequest = { vm.hideDialog() },
        // Título del diálogo
        title = {
            Text(stringResource(R.string.registro))
        },
        // Botón de confirmación
        confirmButton = {
            // Lógica de confirmación en el ViewModel
            TextButton(onClick = {
                vm.showDialog()
                // Navega a la pantalla de login
                navController.navigate(ObjRoutes.LOGIN)
            }) {

                Text(stringResource(R.string.si))
            }

        },
        // Botón de cancelación
        dismissButton = {
            TextButton(onClick = {
                // Oculta el diálogo desde el ViewModel
                vm.hideDialog()
                // Vuelve a la pantalla de Login/Register
                navController.navigate(ObjRoutes.LOGINREG)
            }) {
                Text(stringResource(R.string.no))
            }
        }

    )
}

/**
 * Preview del diálogo para ver cómo se renderiza en Compose Preview.
 * No requiere ViewModel real, se usa un NavController de prueba.
 */
@Preview(showBackground = true)
@Composable
fun DialogPrev() {

    DialogRegisterScreen(
        navController = rememberNavController(),

        )
}
/**NOTAS:
 * 1. **AlertDialog: Componente de Jetpack Compose que permite mostrar un diálogo modal.
 * 2. **onDismissRequest: Callback que se ejecuta cuando el usuario toca fuera del diálogo.
 * 3. **confirmButton / dismissButton: Botones que ejecutan acciones de confirmación o cancelación.
 * 4. **collectAsState()**: Convierte un StateFlow del ViewModel en un estado observable por Compose.
 * 5. **Preview**: Permite ver el diálogo en tiempo de diseño sin ejecutar la app.
 */
