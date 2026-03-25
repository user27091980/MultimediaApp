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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Composable principal que agrupa los campos de texto para registro o login.
 *
 * Contiene campos para:
 * - Email
 * - Contraseña
 * - País
 * - Nombre
 * - Apellido
 *
 * @param email Email actual del usuario.
 * @param pass Contraseña actual del usuario.
 * @param country País del usuario.
 * @param name Nombre del usuario.
 * @param lastName Apellido del usuario.
 * @param onEmailChange Callback que se ejecuta al modificar el email.
 * @param onPassChange Callback que se ejecuta al modificar la contraseña.
 * @param onCountryChange Callback que se ejecuta al modificar el país.
 * @param onNameChange Callback que se ejecuta al modificar el nombre.
 * @param onLastNameChange Callback que se ejecuta al modificar el apellido.
 */
@Composable
fun TextFieldsComponent(

    email: String,
    pass: String,
    country: String,
    name: String,
    lastName: String,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onCountryChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit

) {
    // Columna que organiza los campos verticalmente
    Column {
        TextFieldEmailComponent(
            email = email,
            onEmailChange = onEmailChange
        )
        TextFieldPassComponent(
            pass = pass,
            onPassChange = onPassChange
        )

        TextFieldCountryComponent(
            country = country,
            onCountryChange = onCountryChange
        )
        TextFieldNameComponent(
            name = name,
            onNameChange = onNameChange
        )
        TextFieldLastNameComponent(
            lastName = lastName,
            onLastNameChange = onLastNameChange
        )

    }
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
fun TextFieldEmailComponent(email: String, onEmailChange: (String) -> Unit) {

    // Comprueba si el email es válido
    val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        singleLine = true,
        label = {
            Text("Email", color = MaterialTheme.colorScheme.secondary)
        },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
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
fun TextFieldPassComponent(pass: String, onPassChange: (String) -> Unit) {
    // Estado local que controla si la contraseña se muestra o se oculta
    var passwordVisible by remember { mutableStateOf(false) }
    // Cambia la forma en que se muestra el texto
    OutlinedTextField(
        value = pass,
        onValueChange = onPassChange,
        singleLine = true,
        label = { Text("Password", color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
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
 * Campo de texto para el nombre del usuario.
 *
 * @param name Nombre actual.
 * @param onNameChange Callback que se ejecuta al modificar el nombre.
 */

@Composable
fun TextFieldNameComponent(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        singleLine = true,
        label = { Text("Nombre", color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

/**
 * Campo de texto para el apellido del usuario.
 *
 * @param lastName Apellido actual.
 * @param onLastNameChange Callback que se ejecuta al modificar el apellido.
 */

@Composable
fun TextFieldLastNameComponent(lastName: String, onLastNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = lastName,
        onValueChange = onLastNameChange,
        singleLine = true,
        label = { Text("Apellido", color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

/**
 * Campo de texto para seleccionar el país.
 *
 * @param country País actual.
 * @param onCountryChange Callback que se ejecuta al modificar el país.
 */
@Composable
fun TextFieldCountryComponent(country: String, onCountryChange: (String) -> Unit) {
    OutlinedTextField(
        value = country,
        onValueChange = onCountryChange,
        singleLine = true,
        label = { Text("País", color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )

    )
}
/*
 * Este archivo define un conjunto de campos de texto reutilizables en Jetpack Compose
 * para formularios de autenticación como login o registro de usuario.
 *
 * COMPONENTE PRINCIPAL:
 *
 * TextFieldsComponent(...)
 *
 * - Agrupa varios campos de entrada:
 *   - Email
 *   - Contraseña
 *   - País
 *   - Nombre
 *   - Apellido
 *
 * - Recibe los valores actuales (estado) y callbacks para actualizar cada campo.
 * - Sigue el patrón de "state hoisting":
 *   - El estado vive fuera del composable (normalmente en un ViewModel).
 *   - Este componente solo muestra la UI.
 *
 * ESTRUCTURA:
 *
 * Column:
 * - Organiza los campos de forma vertical.
 *
 * CAMPOS INDIVIDUALES:
 *
 * TextFieldEmailComponent:
 * - Campo para email.
 * - Usa KeyboardType.Email para facilitar la escritura.
 * - Valida el formato del email usando Patterns.EMAIL_ADDRESS.
 * - Muestra un mensaje de error si el email no es válido.
 *
 * TextFieldPassComponent:
 * - Campo para contraseña.
 * - Permite mostrar u ocultar el texto mediante un icono.
 * - Usa VisualTransformation:
 *   - PasswordVisualTransformation() oculta el texto.
 *   - VisualTransformation.None lo muestra.
 * - Incluye un IconButton para alternar la visibilidad.
 *
 * TextFieldNameComponent:
 * - Campo para el nombre del usuario.
 *
 * TextFieldLastNameComponent:
 * - Campo para el apellido del usuario.
 *
 * TextFieldCountryComponent:
 * - Campo para el país del usuario.
 *
 * ESTADO LOCAL:
 *
 * remember { mutableStateOf(false) }
 * - Se utiliza para mantener el estado de visibilidad de la contraseña.
 * - El estado se conserva durante recomposiciones.
 *
 * COMPONENTES DE UI:
 *
 * OutlinedTextField:
 * - Campo de texto con borde.
 * - Muy utilizado en Material Design.
 *
 * Icon + IconButton:
 * - Permiten añadir interacción dentro del campo de texto.
 * - Se usan para mostrar/ocultar la contraseña.
 *
 * KeyboardOptions:
 * - Permite definir el tipo de teclado:
 *   - Email
 *   - Password
 *
 * VisualTransformation:
 * - Controla cómo se muestra el texto (visible u oculto).
 *
 * VALIDACIÓN:
 *
 * - Email:
 *   - Se valida en tiempo real.
 *   - Se muestra mensaje si no es válido.
 *
 * BENEFICIOS:
 *
 * - Código modular y reutilizable.
 * - UI consistente.
 * - Separación de lógica y presentación.
 * - Fácil de mantener y extender.
 *
 * IMPORTANTE:
 *
 * - Este componente NO gestiona el estado global.
 * - El estado debe manejarse en un ViewModel.
 */