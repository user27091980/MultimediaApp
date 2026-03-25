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

/*
 * Pantalla que muestra la información detallada de un usuario.
 *
 * OBJETIVO:
 *
 * - Mostrar los datos de un usuario específico.
 * - Cargar la información desde el ViewModel usando su ID.
 * - Gestionar estados como:
 *   - Cargando
 *   - Datos disponibles
 *   - Error o no encontrado
 *
 * ARQUITECTURA:
 *
 * - Sigue el patrón MVVM.
 * - UserInfoVM gestiona la lógica y los datos.
 * - La UI observa el estado mediante StateFlow.
 *
 * FLUJO DE DATOS:
 *
 * 1. Se obtiene el estado:
 *      val uiState by vm.uiState.collectAsStateWithLifecycle()
 *
 * 2. Cuando cambia el userInfoId:
 *      LaunchedEffect(userInfoId) { vm.loadUser(userInfoId) }
 *
 * 3. El ViewModel actualiza los datos:
 *      uiState.userInfo
 *
 * 4. La UI reacciona automáticamente al cambio.
 *
 * collectAsStateWithLifecycle:
 * - Variante optimizada que respeta el ciclo de vida.
 * - Evita actualizaciones innecesarias cuando la pantalla no está activa.
 *
 * ESTRUCTURA DE LA UI:
 *
 * 1. Box:
 *    - Contenedor principal.
 *    - Permite posicionar elementos superpuestos.
 *
 * 2. Column:
 *    - Muestra el contenido en vertical.
 *    - Añade padding para separar de los bordes.
 *
 * 3. UserCardComponent:
 *    - Muestra los datos del usuario:
 *      - ID
 *      - Email
 *      - Nombre
 *      - Apellido
 *      - País
 *
 * MANEJO DE ESTADOS:
 *
 * if (user != null):
 * - Se muestran los datos del usuario.
 *
 * else:
 * - Se muestra un mensaje de carga:
 *     "Buscando información del usuario..."
 *
 * BOTÓN FLOTANTE:
 *
 * FloatCamera:
 * - Botón situado en la esquina inferior derecha.
 * - Posicionado con:
 *     Modifier.align(Alignment.BottomEnd)
 *
 * - Puede usarse para:
 *   - Abrir cámara
 *   - Capturar imagen
 *   - Ejecutar acción rápida
 *
 * La acción actual está pendiente de implementación.
 *
 * NAVEGACIÓN / PARAMETRO:
 *
 * userInfoId:
 * - Identificador del usuario.
 * - Se usa para filtrar y cargar los datos correctos.
 *
 * BENEFICIOS:
 *
 * - UI reactiva y eficiente.
 * - Separación clara de lógica y presentación.
 * - Uso de lifecycle-aware state.
 * - Reutilización de componentes.
 *
 * NOTAS IMPORTANTES:
 *
 * - LaunchedEffect(userInfoId):
 *   Se ejecuta cuando cambia el ID del usuario.
 *
 * - collectAsStateWithLifecycle():
 *   Mejora el rendimiento respecto a collectAsState().
 *
 * - Box:
 *   Permite superponer elementos como el botón flotante.
 *
 * - El estado de carga actual es implícito (no hay loading explícito).
 */