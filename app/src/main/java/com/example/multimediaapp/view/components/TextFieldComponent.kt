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
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.multimediaapp.R

/**
 * TextFieldsComponent:
 *
 * Composable principal que agrupa los campos de registro o login:
 * - Usuario
 * - Email
 * - Contraseña
 * - País
 * - Nombre
 * - Apellido
 *
 * @param user Texto actual del campo de usuario
 * @param email Texto actual del campo de email
 * @param pass Texto actual del campo de contraseña
 * @param country Texto actual del campo de país
 * @param name Texto actual del campo de nombre
 * @param lastName Texto actual del campo de apellido
 * @param onUserChange Callback para actualizar el usuario
 * @param onEmailChange Callback para actualizar el email
 * @param onPassChange Callback para actualizar la contraseña
 * @param onCountryChange Callback para actualizar el país
 * @param onNameChange Callback para actualizar el nombre
 * @param onLastNameChange Callback para actualizar el apellido
 *
 * Funcionalidad:
 * - Organiza los campos verticalmente en una columna
 * - Reutiliza TextFieldUserComponent, TextFieldEmailComponent, TextFieldPassComponent, etc.
 */
@Composable
fun TextFieldsComponent(
    user: String,
    email: String,
    pass: String,
    country: String,
    name: String,
    lastName: String,
    onUserChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onCountryChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit
) {
    Column {
        TextFieldUserComponent(user = user, onUserChange = onUserChange)
        TextFieldEmailComponent(email = email, onEmailChange = onEmailChange)
        TextFieldPassComponent(pass = pass, onPassChange = onPassChange)
        TextFieldCountryComponent(country = country, onCountryChange = onCountryChange)
        TextFieldNameComponent(name = name, onNameChange = onNameChange)
        TextFieldLastNameComponent(lastName = lastName, onLastNameChange = onLastNameChange)
    }
}

/**
 * Campo de usuario.
 *
 * @param user Texto actual del campo
 * @param onUserChange Callback que se ejecuta al modificar el usuario
 *
 * Funcionalidad:
 * - Muestra mensaje de error si el campo está vacío
 * - Usa stringResource para internacionalización
 */
@Composable
fun TextFieldUserComponent(user: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        value = user,
        onValueChange = onUserChange,
        singleLine = true,
        label = { Text(stringResource(R.string.usuario), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
        supportingText = {
            if (user.isEmpty()) Text("usuario sin rellenar")
        }
    )
}

/**
 * Campo de email.
 *
 * @param email Texto actual
 * @param onEmailChange Callback al modificar el email
 *
 * Funcionalidad:
 * - Valida el formato del email usando Patterns.EMAIL_ADDRESS
 * - Muestra teclado optimizado para email
 * - Muestra mensaje de error si email no es válido
 */
@Composable
fun TextFieldEmailComponent(email: String, onEmailChange: (String) -> Unit) {
    val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        singleLine = true,
        label = { Text(stringResource(R.string.correo), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingText = {
            if (email.isNotEmpty() && !isValidEmail) Text("Email no válido")
        }
    )
}

/**
 * Campo de contraseña con toggle de visibilidad.
 *
 * @param pass Contraseña actual
 * @param onPassChange Callback al modificar la contraseña
 *
 * Funcionalidad:
 * - Muestra u oculta la contraseña usando VisualTransformation
 * - Icono interactivo para alternar visibilidad
 * - Teclado optimizado para contraseña
 */
@Composable
fun TextFieldPassComponent(pass: String, onPassChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = pass,
        onValueChange = onPassChange,
        singleLine = true,
        label = { Text(stringResource(R.string.contraseña), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
 * Campo de nombre.
 */
@Composable
fun TextFieldNameComponent(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        singleLine = true,
        label = { Text(stringResource(R.string.nombrePropio), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

/**
 * Campo de apellido.
 */
@Composable
fun TextFieldLastNameComponent(lastName: String, onLastNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = lastName,
        onValueChange = onLastNameChange,
        singleLine = true,
        label = { Text(stringResource(R.string.apellido), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

/**
 * Campo de país.
 */
@Composable
fun TextFieldCountryComponent(country: String, onCountryChange: (String) -> Unit) {
    OutlinedTextField(
        value = country,
        onValueChange = onCountryChange,
        singleLine = true,
        label = { Text(stringResource(R.string.pais), color = MaterialTheme.colorScheme.secondary) },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

/*
Notas importantes:

1. remember { mutableStateOf("") }:
   - Permite mantener el estado de cada TextField entre recomposiciones.

2. VisualTransformation:
   - Útil para ocultar o mostrar contraseñas de forma segura.

3. trailingIcon + IconButton:
   - Icono interactivo para alternar visibilidad de la contraseña.

4. stringResource:
   - Permite usar textos desde res/values/strings.xml, facilitando la internacionalización.

5. keyboardOptions:
   - Configura el teclado virtual según el tipo de dato (Email, Password, etc.)

6. supportingText:
   - Muestra mensajes de error o validación debajo del TextField.
*/