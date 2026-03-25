package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.FloatCamera
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.vm.UserInfoVM

/**
 * Pantalla que muestra la información de un usuario específico.
 *
 * @param userInfoId ID del usuario cuya información se quiere mostrar.
 * @param vm ViewModel que maneja el estado de la pantalla.
 */
@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    // Usamos collectAsStateWithLifecycle para mayor eficiencia en Android
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Carga los datos cuando cambia el ID
    LaunchedEffect(userInfoId) {
        vm.loadUser(userInfoId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Obtenemos el usuario de la lista filtrando por el ID actual
            val user = uiState.userInfo.find { it.id == userInfoId }

            if (user != null) {
                UserCardComponent(
                    id = user.id,
                    email = user.email,
                    name = user.name,
                    surname = user.lastName,
                    country = user.country,

                    )
            } else {
                // Estado de carga o error
                Text(
                    text = "Buscando información del usuario...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Botón flotante
        FloatCamera(
            onClick = { /* Lógica de cámara */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        )
    }
}

