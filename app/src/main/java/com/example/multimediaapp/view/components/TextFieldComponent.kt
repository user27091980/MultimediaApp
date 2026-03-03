package com.example.multimediaapp.view.components

// Layout vertical para colocar elementos uno debajo del otro
import androidx.compose.foundation.layout.Column
// Opciones del teclado (tipo password, email, etc.)
import androidx.compose.foundation.text.KeyboardOptions
// Iconos de Material
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
// Componentes Material 3
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
// Anotación Composable
import androidx.compose.runtime.Composable
// Delegación de estado
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
// Permite usar strings del archivo strings.xml
import androidx.compose.ui.res.stringResource

// Tipos de teclado y transformación visual
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.multimediaapp.R

/**
 * Componente contenedor de los TextFields
 *
 * Se encarga de agrupar:
 * - Usuario
 * - Contraseña
 * - Email
 */
@Composable
fun TextFieldsComponent() {
    // Coloca los campos uno debajo del otro
    Column {
        TextFieldUserComponent(
            value = TODO(),
            onValueChange = TODO()
        )
        TextFieldPassComponent()
        TextFieldEmailComponent()
    }
}

/**
 * Campo de texto para el usuario
 * @param value
 * @param onValueChange
 */

@Composable
fun TextFieldUserComponent(value: String, onValueChange:(String)->Unit) {
    // Estado que guarda lo que el usuario escribe
    var text by remember { mutableStateOf("") }

    TextField(
        // Valor actual del campo
        value = text,
        // Se ejecuta cada vez que cambia el texto
        onValueChange = { text = it },
        // Solo permite una línea
        singleLine = true,
        // Etiqueta del campo
        label = { Text("user") }
    )
}

/**
 *Campo de texto para la contraseña
 */
@Composable
fun TextFieldPassComponent() {

    // Guarda la contraseña escrita
    var pass by remember { mutableStateOf("") }
    // Controla si la contraseña es visible o no
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        // Valor actual
        value = pass,
        // Actualiza el estado cuando cambia
        onValueChange = { pass = it },
        // Etiqueta
        label = { Text("password") },
        // Solo una línea
        singleLine = true,
        // Si passwordVisible es true → muestra texto normal
        // Si es false → oculta con puntos
        visualTransformation =
            if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        // Configura el teclado como tipo contraseña
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        // Icono al final del TextField
        trailingIcon = {
//cambia el icono del ojo
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // descripción para accesibilidad
            val description = if (passwordVisible) "Hide password" else "Show password"

            // boton para cambiar la visibilidad de la contraseña
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)

            }
        }
    )
}


/**
 *Campo de texto para el email
 */
@Composable
fun TextFieldEmailComponent() {
    // Estado que guarda el email
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = {
            // Usa un string desde strings.xml
            Text(text = stringResource(R.string.correo))
        }
    )
}