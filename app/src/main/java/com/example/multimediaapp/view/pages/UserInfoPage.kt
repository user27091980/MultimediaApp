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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.FloatCamera
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
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
    vm: UserInfoVM = viewModel() // ViewModel asociado al ciclo de vida
) {
    // Observa el StateFlow del ViewModel y lo convierte en un estado Compose
    val uiState by vm.uiState.collectAsState()
    // LaunchedEffect se ejecuta cuando userInfoId cambia o el Composable entra en composición
    // Llama a loadUser() para cargar la información del usuario desde el repositorio
    LaunchedEffect(userInfoId) {
        vm.loadUser(userInfoId)
    }
    // Caja principal que ocupa toda la pantalla
    Box(modifier = Modifier.fillMaxSize()) {
        // Columna para mostrar los componentes verticalmente
        Column {
            // Obtenemos el primer usuario de la lista (si existe)
            val user = uiState.userInfo.firstOrNull()
            // Si el usuario está disponible, mostramos su información en una tarjeta
            if (user != null) {
                UserCardComponent(
                    id = user.id,
                    email = user.email,
                    country = user.country,
                    user = user.user,
                    name = user.name,
                    surname = user.surname
                )
            // Si los datos aún no se han cargado, mostramos un mensaje de carga
            } else {
                Text(
                    text = "Cargando información del usuario...",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        // Componente flotante de cámara en la esquina inferior derecha
        FloatCamera(
            onClick = { /* abrir cámara */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

/**
 * Preview para la pantalla de información del usuario
 * Permite ver cómo se verá el Composable sin ejecutar la app
 */
@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    // Creamos un usuario de prueba
    val previewUser = UserInfoUiState(
        id = "1",
        email = "user@example.com",
        user = "preview_user",
        country = "USA",
        name = "Andrés",
        surname = "García"
    )

    // Creamos un estado inicial con ese usuario
    val previewState = UserInfoListUiState(
        userInfo = listOf(previewUser)
    )

    // Creamos un ViewModel de prueba pasando el estado inicial
    // Aquí usamos Context de Compose (no real en preview)
    val previewVM = UserInfoVM(LocalContext.current, initialState = previewState)

    // Llamamos al Composable con el ViewModel de prueba
    UserInfoScreen(
        userInfoId = "1",
        vm = previewVM
    )
}

