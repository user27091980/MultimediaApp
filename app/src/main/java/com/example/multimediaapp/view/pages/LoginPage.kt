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

@Composable
fun LoginScreen(
    navController: NavController,
    loginVM: LoginVM
) {
    val uiState by loginVM.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    var rememberUser by remember { mutableStateOf(false) }

    // Inicializar checkbox y email desde DataStore
    LaunchedEffect(Unit) {
        loginVM.dataStore.rememberUserFlow
            .combine(loginVM.dataStore.userFlow) { remember, user -> remember to user }
            .collectLatest { (remember, user) ->
                rememberUser = remember
                if (remember && user != null) {
                    loginVM.onUserChange(user.email)
                }
            }
    }

    // Recolectar eventos de navegación o errores
    LaunchedEffect(loginVM) {
        loginVM.events.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> {
                    navController.navigate(ObjRoutes.MAIN) {
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                    }
                }
                is LoginEvent.ShowError -> {
                    println("Error: ${event.message}") // aquí puedes usar Snackbar
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldsLoginComponent(
                    user = uiState.user,
                    pass = uiState.password,
                    onUserChange = loginVM::onUserChange,
                    onPassChange = loginVM::onPasswordChange,
                    passwordVisible = uiState.passwordVisible,
                    togglePasswordVisibility = loginVM::togglePasswordVisibility
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberUser,
                        onCheckedChange = { rememberUser = it }
                    )
                    Text(text = "Recordarme")
                }

                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            ButtonAccept(
                onClick = {
                    if (loginVM.validateFieldsLogin()) {
                        scope.launch {
                            // Guardar preferencias
                            loginVM.dataStore.saveRememberUser(rememberUser)
                            if (rememberUser) loginVM.dataStore.saveUserEmail(uiState.user)

                            // Llamada al ViewModel
                            loginVM.login()
                        }
                    }
                }
            )

            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGINREG) }
            )
        }
    }
}