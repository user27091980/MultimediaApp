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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
/**
 * Composable que representa la barra superior de la aplicación (TopBar)
 *
 * Incluye:
 * - Icono de menú para desplegar opciones
 * - Icono de ajustes para navegar a la pantalla de configuración
 * - Menú desplegable con enlaces externos,añadir banda, logout y opción de cerrar la app
 *
 * @param navController Controlador de navegación de Compose
 */
@Composable
fun TopBar(navController: NavHostController) {
    // Estado que indica si el menú desplegable está abierto o cerrado
    var isExpanded by remember { mutableStateOf(false) }
    // Contexto actual (necesario para abrir enlaces o finalizar la app)
    val context = LocalContext.current
    // Intenta obtener la Activity actual desde el contexto, útil para cerrar la app
    val activity = context as? Activity
    val sessionManager = SessionManager(context)
    // Fila horizontal que contiene los iconos y la barra superior
    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        // Botón de menú
        IconButton(onClick = { isExpanded = true }) {

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menú",
                tint =
                    MaterialTheme.colorScheme.primary,

                )

        }
        //Configuración button
        IconButton(onClick = { navController.navigate(ObjRoutes.SETTINGS) }) {

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "ajustes",
                tint =
                    MaterialTheme.colorScheme.primary,

                )
        }
        //Dropdown menu, se muestra en la esquina superior izquierda
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            //Opción para abrir last.fm web
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
            //opción para abrir  discogs web
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
            //añadir  banda
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.add)) },
                onClick = {
                    isExpanded = false
                    sessionManager.logout()

                    navController.navigate(ObjRoutes.ADDBAND) {
                        popUpTo(0)
                    }
                }
            )
            // Logout
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.logout)) },
                onClick = {
                    isExpanded = false
                    sessionManager.logout()

                    navController.navigate(ObjRoutes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
            //para cerrar la app
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.salir)) },
                onClick = {
                    isExpanded = false
                    activity?.finishAffinity() // cerrar app
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