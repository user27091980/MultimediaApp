package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.FloatCamera
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import com.example.multimediaapp.viewmodel.vm.UserInfoVM

@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Cargar datos cuando cambie el userInfoId
    LaunchedEffect(userInfoId) {
        vm.loadData(userInfoId)
    }

    // Buscar el usuario concreto
    val user = uiState.userInfo.find { it.id == userInfoId }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            if (user != null) {
                // Usuario encontrado → mostrar tarjeta
                UserCardComponent(
                    id = user.id,
                    email = user.email,
                    country = user.country,
                    user = user.user,
                    name = user.name,
                    surname = user.surname
                )
            } else {
                // Usuario no cargado aún → placeholder
                Text(
                    text = "Cargando información del usuario...",
                    color = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        FloatCamera(
            onClick = { /* abrir cámara */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    val previewUser = UserInfoUiState(
        id = "1",
        email = "user@example.com",
        user = "preview_user",
        country = "USA",
        name = "Andrés",
        surname = "García"
    )
    val previewState = UserInfoListUiState(userInfo = listOf(previewUser))

    // Creamos el ViewModel de preview con estado inicial
    val previewVM = UserInfoVM(initialState = previewState)

    UserInfoScreen(userInfoId = "1", vm = previewVM)
}

