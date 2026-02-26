package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.view.components.ButtonAcept
import com.example.multimediaapp.viewmodel.events.LoginEvent
import com.example.multimediaapp.viewmodel.uistate.LoginUiState
import com.example.multimediaapp.viewmodel.vm.LoginVM


/**
 * @author Andrés
 */
@Composable
fun Login(

    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    onLoginClick: () -> Unit

) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black) // verde Spotify → negro
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(horizontal = 32.dp)

        ) {
            Text("LOGIN", color = Color.White)
            //TextFieldUserComponent()
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            //TextFieldPassComponent()
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation =
                    if (uiState.passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    TextButton(onClick = onTogglePassword) {
                        Text(if (uiState.passwordVisible) "Hide" else "Show")
                    }


                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ButtonAcept(onClick = onLoginClick)

        }
    }
}
//creamos el route
@Composable
fun LoginRoute(
    navController: NavHostController,
    viewModel: LoginVM = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    //ESCUCHAMOS EVENTOS DE NAVEGACIÓN
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                LoginEvent.NavigateToHome -> {
                    navController.navigate("home") {
                        popUpTo(ObjRoutes.LOGINREG) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Login(

        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onTogglePassword = viewModel::togglePasswordVisibility,
        onLoginClick = { viewModel.login() }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {

    Login(

        uiState = LoginUiState(
            email = "andresico@mail.com",
            password = "123456",
            passwordVisible = false
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onTogglePassword = {},
        onLoginClick = {}

    )
}