package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.multimediaapp.data.repository.LoginRepo
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.view.components.ButtonAccept
import com.example.multimediaapp.view.components.ButtonCancel
import com.example.multimediaapp.view.components.TextFieldsLoginComponent
import com.example.multimediaapp.viewmodel.vm.LoginEvent
import com.example.multimediaapp.viewmodel.vm.LoginVM
import com.example.multimediaapp.viewmodel.vm.LoginVMFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * LoginScreen:
 *
 * Pantalla de inicio de sesión que permite al usuario ingresar su correo y contraseña.
 * Integra:
 * - Campos de texto para email y contraseña (TextFieldsLoginComponent)
 * - Checkbox para "Recordarme" que guarda email en DataStore
 * - Botones de aceptar y cancelar (ButtonAccept, ButtonCancel)
 * - Manejo de errores y eventos de navegación mediante LoginVM
 *
 * @param navController Controlador de navegación para cambiar de pantalla
 * @param loginVM ViewModel que maneja la lógica de login, estado de campos y eventos
 */
@Composable
fun LoginScreen(
    navController: NavController,
    loginVM: LoginVM
) {
    // Observa el estado de la UI desde el ViewModel
    val uiState by loginVM.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    // Estado local para el checkbox "Recordarme"
    var rememberUser by remember { mutableStateOf(false) }

    /**
     * Inicialización:
     * Obtiene el valor de "Recordarme" y el email del usuario desde DataStore
     * y actualiza el estado del ViewModel si corresponde.
     */
    LaunchedEffect(Unit) {
        loginVM.dataStore.rememberUserFlow
            .combine(loginVM.dataStore.userFlow) { remember, name -> remember to name }
            .collectLatest { (remember, name) ->
                rememberUser = remember
                if (remember && name != null) {
                    loginVM.onUserChange(name.email)
                }
            }
    }

    /**
     * Recolecta eventos del ViewModel:
     * - NavigateToHome: navega a la pantalla principal
     * - ShowError: muestra un mensaje de error (aquí se imprime en consola, se puede usar Snackbar)
     */
    LaunchedEffect(loginVM) {
        loginVM.events.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> {
                    navController.navigate(ObjRoutes.MAIN) {
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                    }
                }
                is LoginEvent.ShowError -> {
                    println("Error: ${event.message}") // Aquí se podría mostrar un Snackbar
                }
            }
        }
    }

    // Contenedor principal centrado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        // Columna principal con padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Sección de campos de login
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldsLoginComponent(
                    name = uiState.name,
                    pass = uiState.password,
                    onUserChange = loginVM::onUserChange,
                    onPassChange = loginVM::onPasswordChange,
                    passwordVisible = uiState.passwordVisible,
                    togglePasswordVisibility = loginVM::togglePasswordVisibility
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Checkbox "Recordarme"
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberUser,
                        onCheckedChange = { rememberUser = it }
                    )
                    Text(text = "Recordarme")
                }

                // Mensaje de error (si existe)
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Botón aceptar: valida campos y ejecuta login
            ButtonAccept(
                onClick = {
                    if (loginVM.validateFieldsLogin()) {
                        scope.launch {
                            // Guardar preferencias en DataStore
                            loginVM.dataStore.saveRememberUser(rememberUser)
                            if (rememberUser) loginVM.dataStore.saveUserEmail(uiState.name)

                            // Ejecutar login desde ViewModel
                            loginVM.login()
                        }
                    }
                }
            )

            // Botón cancelar: vuelve a pantalla login/registro
            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGINREG) }
            )
        }
    }
}

/**
 * Notas explicativas:
 *
 * 1. **TextFieldsLoginComponent**: Composable que contiene los campos de correo y contraseña.
 * 2. **Checkbox "Recordarme"**: Permite guardar el email en DataStore para la próxima sesión.
 * 3. **ButtonAccept / ButtonCancel**: Composables personalizados para acciones de login o cancelar.
 * 4. **LaunchedEffect**: Maneja efectos secundarios, como cargar preferencias o recolectar eventos del ViewModel.
 * 5. **collectAsState()**: Convierte un StateFlow del ViewModel en un estado observable por Compose.
 * 6. **rememberCoroutineScope**: Permite lanzar corutinas dentro del Composable.
 * 7. **DataStoreManager**: Clase encargada de persistir datos locales (email y "Recordarme").
 */