package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.R
import com.example.multimediaapp.ui.theme.styleButtonText

/**
* Componente de botones personalizados para toda la app
* usando Material3 FilledTonalButton.
* Permite reutilizar botones de login, registro, aceptar y cancelar.
*/
/**
 * @author Andres
 * @param onClick Función que se ejecuta al presionar el botón
 * @param modifier Modifier opcional para personalizar tamaño, padding, etc.
 */

//boton login
@Composable
fun ButtonLogin(onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    FilledTonalButton(

        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(20.dp)
    ) {
        Text(stringResource(R.string.login))
    }
}

/**
 * Botón de registro
 *
 * @param onClick Función que se ejecuta al presionar el botón
 * @param modifier Modifier opcional para personalizar tamaño, padding, etc.
 */

//boton para el registro
@Composable
fun ButtonRegister(onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }

    FilledTonalButton(
        onClick = { showDialog = true },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = stringResource(R.string.registro))
    }
    /*if (showDialog) {
        DialogPage(
            onConfirm = {
                navController.navigate(ObjRoutes.REGISTER) // navega al registro
                showDialog = true
            },
            onCancel = {
                showDialog = false // cierra el diálogo
            }
        )
    }*/

}

/**
 * Botón para aceptar acciones
 *
 * @param onClick Función que se ejecuta al presionar el botón
 * @param modifier Modifier opcional
 */
//botón para aceptar
@Composable

fun ButtonAcept(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilledTonalButton(


        onClick = {},
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),


        ) {
        Text(
            text = stringResource(R.string.aceptar),
            style = styleButtonText
        )
    }

}

/**
 * Botón para cancelar acciones
 *
 * @param onClick Función que se ejecuta al presionar el botón (por defecto no hace nada)
 * @param modifier Modifier opcional
 */
//botón de cancelación
@Composable
fun ButtonCancel(onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    FilledTonalButton(

        onClick = { },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),


        ) {
        Text(
            text = stringResource(R.string.cancelar),
            style = styleButtonText
        )
    }

}