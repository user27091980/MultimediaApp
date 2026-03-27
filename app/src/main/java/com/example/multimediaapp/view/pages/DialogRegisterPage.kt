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
 * DialogRegisterScreen:
 *
 * Composable que muestra un diálogo de confirmación cuando un usuario
 * se registra. Permite al usuario confirmar su registro ("Sí") o cancelar
 * la acción ("No").
 *
 * Funciona en conjunto con un ViewModel ([DialogVM]) que mantiene el estado
 * de visibilidad del diálogo mediante un StateFlow.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel encargado de manejar el estado del diálogo. Por defecto se obtiene usando viewModel().
 */
@Composable
fun DialogRegisterScreen(
    navController: NavController,
    vm: DialogVM = viewModel()
) {
    // Observa el estado del diálogo desde el ViewModel
    val uiState by vm.uiState.collectAsState()

    // AlertDialog: Componente de Compose para mostrar un diálogo modal
    AlertDialog(
        // Callback que se ejecuta si el usuario toca fuera del diálogo
        onDismissRequest = { vm.hideDialog() },
        // Título del diálogo
        title = {
            Text(stringResource(R.string.registro))
        },
        // Botón de confirmación ("Sí")
        confirmButton = {
            TextButton(onClick = {
                // Lógica al confirmar
                vm.showDialog() // Actualiza el estado del diálogo en el ViewModel
                navController.navigate(ObjRoutes.INFOUSER) // Navega a pantalla de información del usuario
            }) {
                Text(stringResource(R.string.si))
            }
        },
        // Botón de cancelación ("No")
        dismissButton = {
            TextButton(onClick = {
                // Oculta el diálogo
                vm.hideDialog()
                // Vuelve a la pantalla de login/registro
                navController.navigate(ObjRoutes.LOGINREG)
            }) {
                Text(stringResource(R.string.no))
            }
        }
    )
}

/**
 * Preview del diálogo:
 *
 * Permite visualizar cómo se renderiza el AlertDialog en tiempo de diseño
 * sin necesidad de ejecutar la app completa. Utiliza un NavController simulado.
 */
@Preview(showBackground = true)
@Composable
fun DialogPrev() {
    DialogRegisterScreen(
        navController = rememberNavController()
    )
}

/**
 * Notas explicativas:
 *
 * 1. **AlertDialog**: Composable que muestra un diálogo modal sobre la pantalla actual.
 * 2. **onDismissRequest**: Callback que se ejecuta al tocar fuera del diálogo o presionar "atrás".
 * 3. **confirmButton / dismissButton**: Botones de acción dentro del diálogo; ejecutan la lógica definida.
 * 4. **collectAsState()**: Convierte un StateFlow del ViewModel en un estado observable por Compose.
 * 5. **ViewModel (DialogVM)**: Gestiona la visibilidad y acciones del diálogo mediante un StateFlow.
 * 6. **Preview**: Permite visualizar la UI de Compose en Android Studio sin ejecutar la app.
 */