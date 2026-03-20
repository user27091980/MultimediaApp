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
import com.example.multimediaapp.viewmodel.vm.UserVM

/**
 * Pantalla que muestra la información de un usuario específico.
 *
 * @param userInfoId ID del usuario cuya información se quiere mostrar.
 * @param vm ViewModel que maneja el estado de la pantalla.
 */
@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserVM = viewModel()
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Cargar usuario al entrar
    LaunchedEffect(userInfoId) {
        if (userInfoId.isNotBlank()) {
            vm.loadUserById(userInfoId)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(16.dp)) {

            val user = uiState.user

            if (user != null) {
                UserCardComponent(
                    id = user.id ?: "",
                    email = user.email,
                    name = user.name ?: "",
                    surname = user.lastName ?: "",
                    country = user.country ?: ""
                )
            } else {
                Text(
                    text = "Buscando información del usuario...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        FloatCamera(
            onClick = { /* Lógica de cámara */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        )
    }
}

