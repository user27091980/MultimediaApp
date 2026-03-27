package com.example.multimediaapp.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.multimediaapp.R
import com.example.multimediaapp.navigation.ObjRoutes

/**
 * Componente BottomBar:
 *
 * Representa la barra de navegación inferior de la aplicación (Bottom Navigation).
 * Permite al usuario cambiar entre pantallas principales como:
 * - Inicio (MainScreen)
 * - Perfil / Usuario (UserInfoScreen)
 *
 * ===Estructura===
 * - NavigationBar: contenedor principal de la barra inferior
 * - NavigationBarItem: cada icono + etiqueta que representa una ruta
 * - Icon y Text: muestran el símbolo y el nombre de la pantalla
 *
 * ===Estado===
 * - selectedItem: controla cuál icono está seleccionado actualmente
 * - recordamos el estado con `remember { mutableStateOf(0) }` para que
 *   Compose reaccione a los cambios de selección.
 *
 * ===Lista de items===
 * - Se define una lista `items` de tipo BottomBarItem, con:
 *   - label → texto de la pantalla
 *   - icon → icono visual
 *   - route → ruta de navegación asociada
 *
 * Nota: se puede descomentar el ítem de búsqueda si se agrega esa funcionalidad.
 *
 * ===Navegación===
 * - Al hacer click en un ítem:
 *   1. Se actualiza `selectedItem` para reflejar la selección visual.
 *   2. Se llama `navController.navigate(item.route)` para cambiar de pantalla.
 *   3. Se puede agregar `launchSingleTop = true` para evitar recrear pantallas repetidas.
 *
 * ===Estilo===
 * - `containerColor`: color de fondo de la barra
 * - `contentColor`: color del contenido por defecto
 * - Los iconos y textos cambian de color si están seleccionados, usando:
 *   - MaterialTheme.colorScheme.primary → seleccionado
 *   - MaterialTheme.colorScheme.onPrimaryContainer → no seleccionado
 *
 * ===Ventajas===
 * 1. Centraliza la barra inferior en un solo composable.
 * 2. Fácil de ampliar con más items o rutas.
 * 3. Mantiene consistencia de estilo con Material3.
 *
 * ===Ejemplo de uso===
 * ```
 * val navController = rememberNavController()
 * BottomBar(navController = navController)
 * ```
 */
@Composable
fun BottomBar(navController: NavHostController) {

    // Estado de ítem seleccionado
    var selectedItem by remember { mutableStateOf(0) }

    // Lista de ítems en la barra
    val items = listOf(
        BottomItems.BottomBarItem(
            label = stringResource(R.string.inicio),
            Icons.Default.Home,
            route = ObjRoutes.MAIN
        ),
        /*BottomItems.BottomBarItem(
            label = stringResource(R.string.búsqueda),
            Icons.Default.Search,
        ),*/
        BottomItems.BottomBarItem(
            label = stringResource(R.string.usuario),
            Icons.Default.Person,
            route = ObjRoutes.INFOUSER
        )
    )

    // Componente NavigationBar que contiene los items
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selectedItem == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selectedItem == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}

/**
 * Clase auxiliar BottomItems:
 *
 * Define la estructura de cada ítem de la barra inferior.
 *
 * Propiedades:
 * - label: nombre de la pantalla
 * - icon: icono a mostrar
 * - route: ruta de navegación asociada
 *
 * Ventaja: permite agregar, eliminar o reorganizar ítems fácilmente.
 */
class BottomItems {
    data class BottomBarItem(
        val label: String,
        val icon: ImageVector,
        val route: String
    )
}