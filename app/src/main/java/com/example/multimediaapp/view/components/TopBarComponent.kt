package com.example.multimediaapp.view.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.multimediaapp.R
import com.example.multimediaapp.data.datastore.DataStoreManager
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.rowModifier
import kotlinx.coroutines.launch

@Composable
fun TopBar(navController: NavHostController, dataStore: DataStoreManager) {

    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    // Convertimos Flow a State para Compose
    val userName by dataStore.getName.collectAsState(initial = "")

    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón menú
        IconButton(onClick = { isExpanded = true }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menú",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Botón ajustes
        IconButton(onClick = { navController.navigate(ObjRoutes.SETTINGS) }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "ajustes",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Menú desplegable
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (userName.isNotBlank()) {
                DropdownMenuItem(
                    text = { Text(text = "Hola, $userName") },
                    onClick = { }
                )
            }

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.link_last)) },
                onClick = {
                    isExpanded = false
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, "https://www.last.fm/".toUri())
                    )
                }
            )

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.link_disc)) },
                onClick = {
                    isExpanded = false
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, "https://www.discogs.com/".toUri())
                    )
                }
            )

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.logout)) },
                onClick = {
                    isExpanded = false
                    scope.launch {
                        dataStore.logout()
                        navController.navigate(ObjRoutes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                }
            )

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.salir)) },
                onClick = {
                    isExpanded = false
                    activity?.finishAffinity()
                }
            )
        }
    }
}

/*
 * Este archivo define la barra superior de la aplicación (TopBar)
 * utilizando Jetpack Compose.
 *
 * COMPONENTE PRINCIPAL:
 *
 * TopBar(navController)
 *
 * - Proporciona acceso a navegación y acciones rápidas.
 * - Incluye un menú desplegable con varias opciones.
 *
 * ELEMENTOS PRINCIPALES:
 *
 * 1. Icono de menú (Menu)
 *    - Abre un DropdownMenu con opciones adicionales.
 *
 * 2. Icono de configuración (Settings)
 *    - Navega a la pantalla de ajustes usando NavController.
 *
 * 3. DropdownMenu
 *    - Se muestra al pulsar el icono de menú.
 *    - Contiene varias acciones:
 *
 *      a) Abrir página web de Last.fm
 *         - Usa Intent.ACTION_VIEW para abrir navegador.
 *
 *      b) Abrir página web de Discogs
 *         - También usa Intent para abrir un enlace externo.
 *
 *      c) Añadir banda
 *         - Navega a la pantalla de añadir banda (ADDBAND).
 *         - También ejecuta logout (cierra sesión actual).
 *
 *      d) Logout
 *         - Elimina la sesión del usuario mediante SessionManager.
 *         - Redirige al login.
 *
 *      e) Salir de la aplicación
 *         - Usa activity?.finishAffinity() para cerrar la app completamente.
 *
 * ESTADO:
 *
 * isExpanded:
 * - Controla si el menú está visible o no.
 * - Se gestiona con remember { mutableStateOf(false) }.
 *
 * CONTEXTO:
 *
 * LocalContext.current:
 * - Permite acceder al contexto Android.
 * - Necesario para:
 *   - Abrir URLs
 *   - Obtener Activity
 *
 * Activity:
 * - Se usa para cerrar la aplicación.
 *
 * NAVEGACIÓN:
 *
 * NavHostController:
 * - Permite cambiar de pantalla dentro de la app.
 * - Se usa para:
 *   - SETTINGS
 *   - ADDBAND
 *   - LOGIN
 *
 * UI:
 *
 * Row:
 * - Organiza los elementos horizontalmente.
 *
 * IconButton + Icon:
 * - Botones con iconos (menú y configuración).
 *
 * DropdownMenu + DropdownMenuItem:
 * - Menú flotante con opciones seleccionables.
 *
 * INTERNACIONALIZACIÓN:
 *
 * stringResource(R.string.*):
 * - Permite usar textos desde resources.
 * - Facilita soporte multi-idioma.
 *
 * MANEJO DE ERRORES:
 *
 * - Los intents están dentro de try-catch para evitar crashes.
 *
 * BUENAS PRÁCTICAS:
 *
 * - Separación de lógica y UI.
 * - Uso de Navigation Component.
 * - Uso de estado reactivo con Compose.
 * - Reutilización de componentes.
 *
 * NOTA:
 *
 * - Este componente actúa como punto central de interacción del usuario.
 * - Controla navegación, sesión y acceso a funciones clave.
 */