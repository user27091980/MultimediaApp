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

@Composable
fun TopBar(navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? Activity
    val dataStore = DataStoreManager(context)
    val coroutineScope = rememberCoroutineScope()

    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón de menú
        IconButton(onClick = { isExpanded = true }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menú",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Botón de ajustes
        IconButton(onClick = { navController.navigate(ObjRoutes.SETTINGS) }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "ajustes",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // DropdownMenu
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Abrir Last.fm
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

            // Abrir Discogs
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

            // Logout (coroutine)
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.logout)) },
                onClick = {
                    isExpanded = false
                    coroutineScope.launch {
                        dataStore.logout() // suspend function
                        navController.navigate(ObjRoutes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                }
            )

            // Salir de la app
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