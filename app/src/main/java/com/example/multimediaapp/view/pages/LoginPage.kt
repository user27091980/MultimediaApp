package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.R
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.view.components.ButtonAcept
import com.example.multimediaapp.view.components.ButtonCancel
import com.example.multimediaapp.viewmodel.events.LoginEvent
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
    vm: LoginVM = viewModel()

) {
    // Observa los cambios del estado UI del ViewModel
    val uiState by vm.uiState.collectAsState()
    // LaunchedEffect se ejecuta una vez al entrar en composición
    // Aquí escucha eventos del ViewModel (ej: navegar a la pantalla principal)
    LaunchedEffect(Unit) {
        vm.events.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> {
                    navController.navigate(ObjRoutes.MAIN) {
                        // elimina pantallas previas del stack
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                        // evita duplicar pantallas
                        launchSingleTop = true
                    }
                }
            }
        }
    }
    // Caja principal que ocupa toda la pantalla y tiene un fondo degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black) // degradado de gris oscuro a negro
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Columna que contiene los TextFields y botones
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            // Título
            Text("LOGIN", color = Color.White)
            // Campo de email
            OutlinedTextField(
                // valor del TextField
                value = uiState.email,
                // callback al cambiar texto
                onValueChange = vm::onEmailChange,
                label = { Text(stringResource(R.string.correo)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,              // color del texto
                    cursorColor = Color.White,            // cursor
                    focusedBorderColor = MaterialTheme.colors.onPrimary,     // borde cuando está seleccionado
                    unfocusedBorderColor = Color.White,   // borde normal
                    focusedLabelColor = MaterialTheme.colors.onPrimary,      // label activo
                    unfocusedLabelColor = Color.White     // label normal
                )
            )

            // Campo de contraseña
            OutlinedTextField(
                value = uiState.password,
                onValueChange = vm::onPasswordChange,
                label = { Text(stringResource(R.string.contraseña)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation =
                    if (uiState.passwordVisible) VisualTransformation.None

                    else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,              // color del texto
                    cursorColor = Color.White,            // cursor
                    focusedBorderColor = MaterialTheme.colors.onPrimary,     // borde cuando está seleccionado
                    unfocusedBorderColor = Color.White,   // borde normal
                    focusedLabelColor = MaterialTheme.colors.onPrimary,      // label activo
                    unfocusedLabelColor = Color.White     // label normal
                ),
                // Icono para mostrar u ocultar contraseña
                trailingIcon = {
                    TextButton(onClick = vm::togglePasswordVisibility) {
                        Text(if (uiState.passwordVisible) "Hide" else "Show")
                    }
                }
            )
           // Mostrar mensaje de error si existe
            uiState.errorMessage?.let { error ->
                Text(error, color = MaterialTheme.colors.error)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón aceptar / login
                ButtonAcept(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(ObjRoutes.MAIN) }
                )
                // Botón cancelar / volver
                ButtonCancel(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(ObjRoutes.LOGIN) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(
        navController = navController,
        vm = LoginVM()
    )
}

/**
 * NOTAS
 *
 * - **OutlinedTextField**: Campo de texto con borde, permite customizar colores y visibilidad de la contraseña.
 * - **TextFieldDefaults.outlinedTextFieldColors**: Personaliza colores de texto, borde, cursor y label.
 * - **LaunchedEffect(Unit)**: Ejecuta código solo una vez al entrar en composición; aquí escucha eventos del ViewModel.
 * - **collectAsState()**: Convierte un StateFlow en un estado observable en Compose.
 * - **Row / Column**: Organiza elementos horizontal o verticalmente.
 * - **Spacer**: Añade espacio entre elementos.
 * - **ButtonAcept / ButtonCancel**: Componentes reutilizables para acciones principales y secundarias.
 */

