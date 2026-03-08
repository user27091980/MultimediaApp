package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
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
fun TextFieldsComponent(
    user: String,
    email: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit
) {
    Column {
        TextFieldUserComponent(
            user = user,
            onUserChange = onUserChange
        )
        TextFieldPassComponent(
            pass = pass,
            onPassChange = onPassChange
        )
        TextFieldEmailComponent(
            email = email,
            onEmailChange = onEmailChange
        )
    }
}

@Composable
fun TextFieldUserComponent(user: String, onUserChange: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = onUserChange,
        singleLine = true,
        label = { Text("User") }
    )
}

@Composable
fun TextFieldPassComponent(pass: String, onPassChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = pass,
        onValueChange = onPassChange,
        singleLine = true,
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val desc = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = desc)
            }
        }
    )
}

@Composable
fun TextFieldEmailComponent(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        singleLine = true,
        label = {
            Text("Email")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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