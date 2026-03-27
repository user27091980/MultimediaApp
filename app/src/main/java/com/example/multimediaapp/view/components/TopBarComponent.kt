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
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.rowModifier
import com.example.multimediaapp.session.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * TopBar:
 *
 * Composable que muestra la barra superior de la aplicación.
 * Contiene:
 * - Botón de menú (abre un DropdownMenu con enlaces externos y acciones)
 * - Botón de ajustes (navega a la pantalla de configuración)
 *
 * Funcionalidades principales:
 * - Abrir enlaces externos como Last.fm y Discogs usando Intent.ACTION_VIEW
 * - Realizar logout de la sesión usando DataStoreManager y coroutines
 * - Cerrar la aplicación (finishAffinity)
 *
 * @param navController Controlador de navegación de Jetpack Compose para movernos entre pantallas
 *
 * Estado:
 * - isExpanded: controla si el menú desplegable está abierto o cerrado
 */
@Composable
fun TopBar(navController: NavHostController) {
    // Estado local que recuerda si el DropdownMenu está abierto
    var isExpanded by remember { mutableStateOf(false) }

    // Contexto de la app y actividad actual
    val context = LocalContext.current
    val activity = context as? Activity

    // Instancia de DataStore para manejar sesión/usuario
    val dataStore = DataStoreManager(context)

    // CoroutineScope para ejecutar funciones suspend dentro de Compose
    val coroutineScope = rememberCoroutineScope()

    // Fila horizontal que contiene los iconos de menú y ajustes
    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón de menú (abre DropdownMenu)
        IconButton(onClick = { isExpanded = true }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menú",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Botón de ajustes (navega a pantalla de configuración)
        IconButton(onClick = { navController.navigate(ObjRoutes.SETTINGS) }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "ajustes",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // DropdownMenu: se despliega cuando isExpanded = true
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Opción: Abrir Last.fm
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

            // Opción: Abrir Discogs
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

            // Opción: Logout (borra sesión y navega al login)
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.logout)) },
                onClick = {
                    isExpanded = false
                    coroutineScope.launch {
                        dataStore.logout() // Función suspend que limpia la sesión
                        navController.navigate(ObjRoutes.LOGIN) {
                            popUpTo(0) // Limpia el stack de navegación
                        }
                    }
                }
            )

            // Opción: Salir de la aplicación
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.salir)) },
                onClick = {
                    isExpanded = false
                    activity?.finishAffinity() // Cierra todas las actividades y termina la app
                }
            )
        }
    }
}

/*
Notas importantes:

1. remember { mutableStateOf(false) }:
   - Permite mantener el estado de expansión del DropdownMenu entre recomposiciones.

2. LocalContext.current:
   - Obtiene el contexto actual de la app, necesario para Intents y DataStore.

3. DataStoreManager:
   - Se usa para manejar la sesión de usuario, permite hacer logout de manera persistente.

4. coroutineScope.launch { ... }:
   - Permite ejecutar funciones suspend dentro de Compose, como logout.

5. Intent.ACTION_VIEW + toUri():
   - Abre URLs externas en el navegador predeterminado del dispositivo.

6. activity?.finishAffinity():
   - Cierra la app completamente, terminando todas las actividades.
*/