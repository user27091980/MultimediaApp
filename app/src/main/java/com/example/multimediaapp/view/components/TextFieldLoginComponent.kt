package com.example.multimediaapp.view.components

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
 * TextFieldsLoginComponent:
 *
 * Composable principal que agrupa los campos de inicio de sesión o registro:
 * - Campo de usuario/correo
 * - Campo de contraseña con toggle de visibilidad
 *
 * @param user Texto actual del campo de usuario
 * @param pass Texto actual del campo de contraseña
 * @param onUserChange Callback para actualizar el valor del usuario
 * @param onPassChange Callback para actualizar el valor de la contraseña
 * @param passwordVisible Boolean opcional para indicar si la contraseña es visible
 * @param togglePasswordVisibility Callback opcional para alternar visibilidad
 *
 * Funcionalidad:
 * - Organiza los campos en una columna
 * - Reutiliza TextFieldLoginUserComponent y TextFieldLoginPassComponent
 */
@Composable
fun TextFieldsLoginComponent(
    user: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    passwordVisible: Boolean = false,
    togglePasswordVisibility: () -> Unit = {}
) {
    Column {
        TextFieldLoginUserComponent(
            user = user,
            onUserChange = onUserChange
        )
        TextFieldLoginPassComponent(
            passwd = pass,
            onPassChange = onPassChange
        )
    }
}


/**
 * TextFieldLoginPassComponent:
 *
 * Campo de texto para introducir la contraseña.
 * Incluye un icono que alterna visibilidad entre texto normal y oculto.
 *
 * @param pass Texto actual de la contraseña
 * @param onPassChange Callback que se ejecuta al cambiar el texto
 *
 * Detalles de implementación:
 * - PasswordVisualTransformation oculta el texto
 * - VisualTransformation.None muestra el texto
 * - trailingIcon con IconButton permite alternar visibilidad
 * - KeyboardOptions ajusta el teclado al tipo Password
 */
@Composable
fun TextFieldLoginPassComponent(passwd: String, onPassChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = passwd,
        onValueChange = onPassChange,
        singleLine = true,
        label = { Text(stringResource(R.string.contraseña), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
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


/**
 * TextFieldLoginUserComponent:
 *
 * Campo de texto para introducir el usuario o correo electrónico.
 *
 * @param user Texto actual del campo
 * @param onUserChange Callback que se ejecuta al modificar el campo
 *
 * Detalles:
 * - singleLine limita a una sola línea
 * - keyboardOptions puede configurarse para tipo Email si se desea
 * - supportingText muestra mensaje de error si el campo está vacío
 * - stringResource permite internacionalización del label
 */
@Composable
fun TextFieldLoginUserComponent(user: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        value = user,
        onValueChange = onUserChange,
        singleLine = true,
        label = {
            Text(stringResource(R.string.usuario), color = MaterialTheme.colorScheme.secondary)
        },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
        supportingText = {
            if (user.isEmpty()) {
                Text("usuario vacío")
            }
        }
    )
}

/*
Notas adicionales:

1. remember { mutableStateOf("") }:
   - Guarda el estado local de cada TextField y lo recuerda entre recomposiciones de Compose.

2. VisualTransformation:
   - Permite mostrar u ocultar contraseñas de manera segura.

3. trailingIcon:
   - Icono interactivo dentro del TextField para alternar visibilidad.

4. stringResource:
   - Permite usar textos desde res/values/strings.xml para internacionalización.

5. singleLine:
   - Limita la entrada a una sola línea y mejora la usabilidad.

6. supportingText:
   - Permite mostrar mensajes de error o indicaciones debajo del TextField.
*/