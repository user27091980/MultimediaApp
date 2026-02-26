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
 * @author="Andrés"
 * @param
 * @function
 */
/*
Se encarga de gestionar los TextFields y añadir sus etiquetas
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
 * @param
 * @function
 */
@Composable
fun TextFieldUserComponent() {

    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text("user") }
    )
}

/**
 * @param
 * @function
 */
@Composable
fun TextFieldPassComponent() {

    // Creating a variable to store passwords
    var pass by remember { mutableStateOf("") }
    // Creating a variable to store toggle state
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(

        value = pass,
        onValueChange = { pass = it },
        label = { Text("password") },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
//cambia el icono del ojo
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)

            }
        }
    )
}

/**
 * @param
 * @function
 */
@Composable
fun TextFieldEmailComponent() {

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