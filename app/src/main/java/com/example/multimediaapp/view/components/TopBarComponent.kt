package com.example.multimediaapp.view.components

// Permite acceder a la Activity actual
import android.app.Activity
// Permite lanzar otras aplicaciones o abrir enlaces
import android.content.Intent
// Layout horizontal
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
// Iconos de Material
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
// Componentes Material 3
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
// Compose runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
// Permite acceder al contexto actual (Activity, etc.)
import androidx.compose.ui.platform.LocalContext
// Permite usar strings.xml
import androidx.compose.ui.res.stringResource
// Convierte String en Uri
import androidx.core.net.toUri
// Controlador de navegación
import androidx.navigation.NavHostController
import com.example.multimediaapp.R
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.rowModifier

/**
 * 🔹 TopBar personalizada
 *
 * Contiene:
 * - Botón menú
 * - Botón ajustes
 * - Dropdown con opciones externas
 */
/**
 * @param navController
 */
@Composable
fun TopBar(navController: NavHostController) {
    // 🔹 Controla si el menú desplegable está abierto o cerrado
    var isExpanded by remember { mutableStateOf(false) }
    // 🔹 Obtiene el contexto actual
    val context = LocalContext.current
    // 🔹 Intenta obtener la Activity actual para poder cerrar la app
    val activity = context as? Activity
    // 🔹 Fila que contiene los botonesr
    Row(
        rowModifier,// Modifier personalizado
        verticalAlignment = Alignment.CenterVertically
    )
    {
        //Botón del menú
        IconButton(onClick = { isExpanded = true }) {

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menú",
                tint =
                    MaterialTheme.colorScheme.primary,

                )

        }
        //Configuración botón
        IconButton(onClick = {
            navController.navigate(ObjRoutes.SETTINGS)
        }) {

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "ajustes",
                tint =
                    MaterialTheme.colorScheme.primary,

                )
        }
        // 🔹 Botón de menú (abre el Dropdown)
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            //Opción abrir last.fm web
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
            //opción discogs web
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
            //opción cerrar la app
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.salir)) },
                onClick = {
                    isExpanded = false
                    // Cierra todas las Activities y finaliza la app
                    activity?.finishAffinity() // cerrar app
                }
            )

        }
    }
}