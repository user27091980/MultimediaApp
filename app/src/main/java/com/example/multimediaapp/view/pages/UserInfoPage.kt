package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
    val uiState by vm.uiState.collectAsState()


    // Cargar datos cuando cambie el userInfoId
    LaunchedEffect(userInfoId) {
        vm.loadUser(userInfoId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            val user = uiState.userInfo.firstOrNull()

            if (user != null) {
                UserCardComponent(
                    id = user.id,
                    email = user.email,
                    country = user.country,
                    user = user.user,
                    name = user.name,
                    surname = user.surname
                )
            } else {
                Text(
                    text = "Cargando información del usuario...",
                    color = MaterialTheme.colorScheme.tertiary,
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

@Preview
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
    val previewVM = UserInfoVM(initialState = previewState)

    UserInfoScreen(userInfoId = "1", vm = previewVM)
}

