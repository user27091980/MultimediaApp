package com.example.multimediaapp.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import com.example.multimediaapp.R


/**
 * @author: Andrés
 * @function
 */
/*
Función que se encarga de la barra inferior(BottomBar), con sus correspondientes iconos
guardados en una lista denominada como items.
 */

@Composable
fun BottomBar() {

    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomItems.BottomBarItem(
            label = stringResource(R.string.inicio),
            Icons.Default.Home,
            route = ""//navController.navigate(ObjRoutes.MAINSCREEN)
        ),
        /*BottomItems.TopButtonItems(
            label = stringResource(R.string.buscar),
            Icons.Default.Search,


        ),*/
        BottomItems.BottomBarItem(
            label = stringResource(R.string.usuario),
            Icons.Default.Person,
            route =""
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

                    //{ launchSingleTop = true }
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