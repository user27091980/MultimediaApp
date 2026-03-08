package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.multimediaapp.R

/**
 * Composable principal que agrupa todos los TextFields de login/registro.
 *
 * Contiene:
 * - TextFieldUserComponent_ para ingresar el nombre de usuario
 * - TextFieldPassComponent:para ingresar la contraseña con toggle de visibilidad
 * - TextFieldEmailComponent: para ingresar el correo electrónico
 */
@Composable
fun TextFieldsComponent() {

    Column {
        TextFieldUserComponent()
        TextFieldPassComponent()
        TextFieldEmailComponent()
    }
}

/**
 * TextField para ingresar el nombre de usuario
 */
@Composable
fun TextFieldUserComponent() {
    // Estado interno para almacenar el texto ingresado
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,// Valor actual del TextField
        onValueChange = { text = it }, // Actualiza el estado al escribir
        singleLine = true,// Una sola línea de texto
        label = { Text("user") } // Etiqueta que se muestra arriba del campo
    )
}

/**
 * TextField para ingresar la contraseña
 * Permite mostrar/ocultar la contraseña usando un icono de ojo
 */
@Composable
fun TextFieldPassComponent() {

    // Estado para almacenar la contraseña ingresada
    var pass by remember { mutableStateOf("") }
    // Estado para controlar si la contraseña es visible o no
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(

        value = pass,
        onValueChange = { pass = it },

        label = { Text("password") },
        singleLine = true,
        // Si passwordVisible es true, se muestra texto plano; si no, se oculta
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        // Teclado de tipo contraseña
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        // Icono que permite alternar la visibilidad de la contraseña
        trailingIcon = {
        //cambia el icono del ojo
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // descripción para accesibiliad
            val description = if (passwordVisible) "Hide password" else "Show password"

            // Botón que cambia el estado passwordVisible al presionar
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)

            }
        }
    )
}

/**
 * TextField para ingresar el correo electrónico
 */
@Composable
fun TextFieldEmailComponent() {
    // Estado para almacenar el correo ingresad
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = {
            Text(text = stringResource(R.string.correo))
        }
    )
}
/*
remember { mutableStateOf("") } nos guarda el estado local de cada TextField, Compose lo recuerda entre recomposiciones.

TextField campo de texto editable con propiedades para controlar estilo, teclado, visualización de texto, etc.

VisualTransformation  transforma cómo se muestra el texto; útil para ocultar contraseñas.

trailingIcon permite colocar un icono dentro del TextField.

IconButton + Icon es el botón interactivo dentro del TextField que alterna visibilidad.

stringResource nos permite usar strings desde res/values/strings.xml, útil para internacionalización.

 */