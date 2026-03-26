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
    dataStore: DataStoreManager,
    repository: LoginRepo,
) {
    val factory = LoginVMFactory(dataStore, repository)
    val vm: LoginVM = viewModel(factory = factory)
    val uiState by vm.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    var rememberUser by remember { mutableStateOf(false) }

    // Inicializar checkbox y email desde DataStore
    LaunchedEffect(Unit) {
        dataStore.rememberUserFlow
            .combine(dataStore.userFlow) { remember, user -> remember to user }
            .collectLatest { (remember, user) ->
                rememberUser = remember
                if (remember && user != null) {
                    vm.onEmailChange(user.email)
                }
            }
    }

    // Recolectar eventos de navegación o errores
    LaunchedEffect(vm) {
        vm.events.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> {
                    navController.navigate(ObjRoutes.MAIN) {
                        popUpTo(ObjRoutes.LOGINREG) { inclusive = true }
                    }
                }

                is LoginEvent.ShowError -> {
                    println("Error: ${event.message}") // aquí podrías mostrar un Snackbar
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
                    email = uiState.email,
                    pass = uiState.password,
                    onEmailChange = vm::onEmailChange,
                    onPassChange = vm::onPasswordChange,
                    passwordVisible = uiState.passwordVisible,
                    togglePasswordVisibility = vm::togglePasswordVisibility
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

                    if (vm.validateFieldsLogin()) {
                        scope.launch {
                            // Guardar preferencias
                            dataStore.saveRememberUser(rememberUser)
                            if (rememberUser) dataStore.saveUserEmail(uiState.email)

                            // Llamada al ViewModel, NO pasamos rememberUser
                            vm.login()
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