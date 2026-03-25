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
                navController.navigate(ObjRoutes.INFOUSER)
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
        })
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
/*
 * Este archivo define un diálogo de confirmación en Jetpack Compose
 * para el proceso de registro de usuario.
 *
 * COMPONENTE PRINCIPAL:
 *
 * DialogRegisterScreen(...)
 *
 * - Muestra un AlertDialog con opciones de confirmación.
 * - Está conectado a un ViewModel (DialogVM).
 * - Permite navegar entre pantallas según la acción del usuario.
 *
 * ESTADO:
 *
 * uiState:
 * - Se obtiene del ViewModel usando collectAsState().
 * - Permite que la UI sea reactiva a cambios de estado.
 *
 * AlertDialog:
 *
 * - Componente de Material Design para mostrar diálogos modales.
 * - Bloquea la interacción con el resto de la pantalla hasta que se cierre.
 *
 * ELEMENTOS DEL DIÁLOGO:
 *
 * 1. onDismissRequest:
 *    - Se ejecuta cuando el usuario toca fuera del diálogo.
 *    - Llama a vm.hideDialog() para cerrar el diálogo.
 *
 * 2. title:
 *    - Título del diálogo (texto "registro").
 *
 * 3. confirmButton:
 *    - Botón de confirmación ("sí").
 *    - Acciones:
 *        - Llama a vm.showDialog()
 *        - Navega a la pantalla INFOUSER
 *
 * 4. dismissButton:
 *    - Botón de cancelación ("no").
 *    - Acciones:
 *        - Llama a vm.hideDialog()
 *        - Navega de vuelta a LOGINREG
 *
 * NAVEGACIÓN:
 *
 * NavController:
 * - Permite cambiar de pantalla dentro de la app.
 * - Se utiliza para:
 *   - INFOUSER
 *   - LOGINREG
 *
 * INTERNACIONALIZACIÓN:
 *
 * stringResource(R.string.*):
 * - Permite usar textos desde archivos de recursos.
 * - Facilita soporte multi-idioma.
 *
 * VIEWMODEL:
 *
 * DialogVM:
 * - Gestiona la lógica del diálogo.
 * - Controla si se muestra o se oculta.
 *
 * PREVIEW:
 *
 * DialogPrev:
 * - Permite visualizar el diálogo sin ejecutar la app.
 * - Usa rememberNavController() como controlador ficticio.
 *
 * BENEFICIOS:
 *
 * - Separación de lógica y UI.
 * - UI reactiva basada en estado.
 * - Código reutilizable y mantenible.
 * - Integración con navegación de forma sencilla.
 *
 * NOTA:
 *
 * - Este componente sigue el patrón MVVM.
 * - La UI depende completamente del estado del ViewModel.
 */