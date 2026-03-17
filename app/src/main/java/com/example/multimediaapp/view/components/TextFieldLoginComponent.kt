package com.example.multimediaapp.view.components

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Composable principal que agrupa todos los TextFields de login/registro.
 *
 * Contiene:
 * -
 * - TextFieldLoginPassComponent:para ingresar la contraseña con toggle de visibilidad
 * - TextFieldEmailLoginComponent: para ingresar el correo electrónico
 */
@Composable
fun TextFieldsLoginComponent(
    email: String,
    pass: String,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit
) {
    // Columna que organiza los campos verticalmente
    Column {
        TextFieldLoginEmailComponent(
            email = email,
            onEmailChange = onEmailChange
        )
        TextFieldLoginPassComponent(
            pass = pass,
            onPassChange = onPassChange
        )

    }
}


/**
 * Campo de texto para introducir la contraseña.
 *
 * Incluye un icono que permite alternar entre mostrar u ocultar
 * el contenido del campo.
 *
 * @param pass texto actual de la contraseña
 * @param onPassChange callback que se ejecuta al modificar la contraseña
 */
@Composable
fun TextFieldLoginPassComponent(pass: String, onPassChange: (String) -> Unit) {
    // Estado local que controla si la contraseña se muestra o se oculta
    var passwordVisible by remember { mutableStateOf(false) }
    // Cambia la forma en que se muestra el texto
    OutlinedTextField(
        value = pass,
        onValueChange = onPassChange,
        singleLine = true,
        label = { Text("Password", color = Color.White) },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        // Cambia el tipo de teclado mostrado
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        // Icono al final del campo para mostrar/ocultar la contraseña
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val desc = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = desc)
            }
        }
    )
}
/**
 * Campo de texto para introducir el correo electrónico.
 *
 * Configura el teclado en modo email para facilitar la escritura.
 *
 * @param email texto actual del campo
 * @param onEmailChange callback que se ejecuta al modificar el email
 */
@Composable
fun TextFieldLoginEmailComponent(email: String, onEmailChange: (String) -> Unit) {

    // Comprueba si el email es válido
    val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        //colors = MaterialTheme.colors.background,
        singleLine = true,
        label = {
            Text("Email", color = Color.White)

        },
        // Muestra teclado optimizado para escribir correos
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        // Mensaje de error debajo del campo
        supportingText = {
            if (email.isNotEmpty() && !isValidEmail) {
                Text("Email no válido")
            }
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