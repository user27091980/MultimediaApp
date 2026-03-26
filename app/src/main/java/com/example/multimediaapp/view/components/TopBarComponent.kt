package com.example.multimediaapp.view.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.multimediaapp.R
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.rowModifier
import com.example.multimediaapp.session.SessionManager
import kotlinx.coroutines.launch

/**
 * TopBar: Barra superior de la aplicación
 *
 * Contiene:
 * - Icono de menú para desplegar opciones
 * - Icono de ajustes para navegar a configuración
 * - Menú desplegable con enlaces externos, logout y cerrar app
 */
@Composable
fun TopBar(navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? Activity
    val sessionManager = SessionManager(context)
    val scope = rememberCoroutineScope()

    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón menú
        IconButton(onClick = { isExpanded = true }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Botón ajustes
        IconButton(onClick = { navController.navigate(ObjRoutes.SETTINGS) }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Menú desplegable
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Enlace Last.fm
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.link_last)) },
                onClick = {
                    isExpanded = false
                    try {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, "https://www.last.fm/".toUri())
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            )

            // Enlace Discogs
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.link_disc)) },
                onClick = {
                    isExpanded = false
                    try {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, "https://www.discogs.com/".toUri())
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            )

            // Logout
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.logout)) },
                onClick = {
                    isExpanded = false
                    scope.launch {
                        sessionManager.logout()  // suspending function
                        navController.navigate(ObjRoutes.LOGIN) {
                            popUpTo(0)  // limpia el backstack
                        }
                    }
                }
            )

            // Cerrar app
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
remember { mutableStateOf(false) } mantiene el estado abierto/cerrado del menú entre recomposiciones.

LocalContext.current  obtiene el contexto de Compose, necesario para abrir enlaces web o interactuar con la Activity.

Row organiza los iconos de manera horizontal.

IconButton + Icon botones interactivos con iconos, aquí para menú y ajustes.

DropdownMenu menú desplegable que se muestra sobre otros elementos.

DropdownMenuItem cada opción del menú; puede ejecutar acciones como abrir URL o cerrar app.

finishAffinity() cierra todas las actividades y termina la app de manera segura.

stringResource() obtiene texto desde strings.xml para internacionalización.
 */