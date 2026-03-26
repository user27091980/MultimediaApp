package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.multimediaapp.data.repository.LoginRepo
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.view.components.ButtonAccept
import com.example.multimediaapp.view.components.ButtonCancel
import com.example.multimediaapp.view.components.TextFieldsLoginComponent
import com.example.multimediaapp.viewmodel.vm.LoginEvent
import com.example.multimediaapp.viewmodel.vm.LoginVM
import com.example.multimediaapp.viewmodel.vm.LoginVMFactory


/**
 * Pantalla de login.
 *
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param vm ViewModel que maneja el estado y la lógica del login.
 */
@Composable
fun LoginScreen(
    navController: NavController,
    repo: LoginRepo = LoginRepo(), // instancia del repo
    vm: LoginVM = viewModel(
        factory = LoginVMFactory(repo) // inyecta repo en el ViewModel
    )
) {
    // Obtenemos el estado actual del ViewModel
    val uiState by vm.uiState.collectAsState()

    // Manejo de eventos de navegación (NavigateToHome)
    LaunchedEffect(Unit) {
        vm.events.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> {
                    navController.navigate(ObjRoutes.MAIN) {
                        // Limpiar el stack de navegación para que no se pueda volver al login con el botón Atrás
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                    }
                }
                is LoginEvent.ShowError -> {
                    // Aquí se podría mostrar un Toast o Snackbar si se desea
                }
                else -> {}
            }
        }
    }

    // Caja principal que ocupa toda la pantalla
    Box(
        modifier = boxModifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldsLoginComponent(
                    email = uiState.email,
                    pass = uiState.password,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPasswordChange
                )
                
                // Mostramos error si existe
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

            // Botón "Aceptar" que ahora llama al login del ViewModel
            ButtonAccept(
                onClick = {
                    vm.login()
                }
            )

            // Botón "Cancelar" que regresa a la pantalla de inicio
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
    LoginScreen(navController = navController)
}
