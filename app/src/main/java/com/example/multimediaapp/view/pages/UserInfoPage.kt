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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.R
import com.example.multimediaapp.view.components.FloatCamera
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.vm.UserInfoVM

/**
 * UserInfoScreen:
 *
 * Pantalla que muestra la información detallada de un usuario específico.
 *
 * Funcionalidades principales:
 * - Obtiene los datos de un usuario a partir de su ID usando el ViewModel.
 * - Muestra los datos del usuario en un componente de tarjeta reutilizable.
 * - Incluye un botón flotante (FloatCamera) para acciones adicionales como abrir la cámara.
 *
 * @param userInfoId ID del usuario cuya información se desea mostrar.
 * @param vm ViewModel que gestiona la información del usuario.
 */
@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    // Estado observable de la UI del ViewModel con manejo de ciclo de vida
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Efecto que se lanza cuando cambia el ID del usuario
    // Carga los datos del usuario desde el ViewModel
    LaunchedEffect(userInfoId) {
        vm.loadUser(userInfoId)
    }

    // Contenedor principal de la pantalla
    Box(modifier = Modifier.fillMaxSize()) {

        // Columna principal para organizar la información verticalmente
        Column(modifier = Modifier.padding(16.dp)) {

            // Filtramos la lista de usuarios por el ID actual
            val user = uiState.userInfo.find { it.id == userInfoId }

            if (user != null) {
                // Muestra la información del usuario en un componente de tarjeta
                UserCardComponent(
                    id = user.id,
                    email = user.email,
                    name = user.name

                )
            } else {
                // Estado de carga o mensaje temporal si no se encuentra el usuario
                Text(
                    text = stringResource(R.string.busquedaUsuario),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Botón flotante en la esquina inferior derecha
        FloatCamera(
            onClick = { /* Lógica de cámara */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        )
    }
}

/**
 * Notas / teoría:
 *
 * 1. **collectAsStateWithLifecycle()**: Observa un StateFlow del ViewModel y se actualiza solo
 *    mientras la UI está activa, evitando fugas de memoria y recomposiciones innecesarias.
 * 2. **LaunchedEffect(userInfoId)**: Se ejecuta cuando cambia el userInfoId. Ideal para cargar datos iniciales.
 * 3. **UserCardComponent**: Componente reutilizable que muestra la información de un usuario.
 * 4. **FloatCamera**: Botón flotante que permanece sobre otros elementos, útil para acciones rápidas.
 * 5. **Box + Column**: Box permite posicionar el FloatCamera en la esquina inferior, mientras Column organiza
 *    la información verticalmente.
 * 6. **Manejo de null**: Si el usuario no se encuentra, se muestra un mensaje de carga o error.
 */