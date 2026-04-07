package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.multimediaapp.R
import com.example.multimediaapp.view.components.FloatCamera
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.vm.UserInfoVM

/**
 * UserInfoScreen adaptada para mostrar la información del perfil
 * sin incluir funciones de logout en esta vista.
 */
@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserInfoVM
) {
    // Estado observable de la UI
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Carga los datos del usuario si no están ya en el estado
    LaunchedEffect(userInfoId) {
        vm.loadUser(userInfoId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Título de la sección
            Text(
                text = stringResource(R.string.infoUsuario),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Buscamos el usuario cargado en la lista del estado
            val user = uiState.userInfo.find { it.id == userInfoId }

            if (user != null) {
                // Componente visual de tarjeta con los datos del DTO
                UserCardComponent(

                    email = user.email,
                    name = user.name,
                    surname = user.lastName,
                    country = user.country,
                )
            } else {
                // Indicador de carga mientras el repositorio responde
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Acción de cámara flotante
        FloatCamera(
            onClick = { /* Lógica de cámara heredada */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserInfoScreenPreview() {
    // Simulamos el diseño sin necesidad de un ViewModel real
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Información de Usuario", // Simula el stringResource
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Simulamos un UserCardComponent con datos de prueba
                UserCardComponent(
                    
                    email = "usuario@ejemplo.com",
                    name = "Juan",
                    surname = "Pérez",
                    country = "España"
                )
            }

            FloatCamera(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
            )
        }
    }
}
