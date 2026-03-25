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
 * @author: Andrés
 * @function
 */
/*
Función que se encarga de la barra inferior(BottomBar), con sus correspondientes iconos
guardados en una lista denominada como items.
 */

@Composable
fun BottomBar(navController: NavHostController) {

    var selectedItem by remember { mutableStateOf(0) }

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


//barra de navegación.
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
                //launchSingleTop opción de navegación que le dice al NavController:
                //“Si ya estoy en esta pantalla, NO la vuelvas a crear”
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)


                }
            )
        }
    }

}

class BottomItems {
    data class BottomBarItem(
        val label: String,
        val icon: ImageVector,
        val route: String
    )
}

/*
 * Este archivo define el componente de la barra de navegación inferior (BottomBar)
 * utilizando Jetpack Compose.
 *
 * La BottomBar permite al usuario navegar entre las diferentes secciones de la app
 * de forma rápida mediante iconos y etiquetas.
 *
 * COMPONENTE PRINCIPAL:
 *
 * BottomBar(navController: NavHostController)
 * - Recibe el NavController para gestionar la navegación entre pantallas.
 * - Mantiene un estado interno (selectedItem) para saber qué opción está activa.
 *
 * selectedItem:
 * - Variable de estado que guarda el índice del elemento seleccionado.
 * - Se usa remember para conservar el estado durante recomposiciones.
 *
 * items:
 * - Lista de elementos de la barra de navegación.
 * - Cada elemento contiene:
 *   - label: texto que se muestra
 *   - icon: icono representativo
 *   - route: ruta a la que navega
 *
 * NavigationBar:
 * - Componente de Material 3 que representa la barra inferior.
 * - Contiene múltiples NavigationBarItem.
 *
 * NavigationBarItem:
 * - Representa cada botón dentro de la barra.
 * - Incluye:
 *   - Icon: icono visual del botón
 *   - Text: etiqueta del botón
 *   - selected: indica si está activo
 *   - onClick: acción al pulsar el botón
 *
 * NAVEGACIÓN:
 * - Al hacer click en un item:
 *   1. Se actualiza el estado selectedItem.
 *   2. Se navega a la ruta correspondiente usando navController.navigate().
 *
 * ESTILO:
 * - Se utilizan colores del MaterialTheme para mantener coherencia visual.
 * - El icono y el texto cambian de color cuando están seleccionados.
 *
 * OBJETO BottomItems:
 * - Contiene la data class BottomBarItem.
 * - Se utiliza para estructurar los datos de cada elemento de la barra.
 *
 * VENTAJAS:
 * - Navegación clara y accesible para el usuario.
 * - Código organizado y reutilizable.
 * - Separación de datos (items) y UI (BottomBar).
 *
 * NOTA:
 * - Se podría mejorar evitando recrear la navegación si ya estás en la misma ruta,
 *   utilizando launchSingleTop o controlando el backstack.
 */