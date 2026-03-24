package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TextFieldAdd(

    name: String,
    description: String,
    style: String,
    recordLabel: String,
    components: String,
    albumLinks: List<String>,
    headerLink: String,
    onAddNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onStyleChange: (String) -> Unit,
    onRecordLabelChange: (String) -> Unit,
    onComponentsChange: (String) -> Unit,
    onAlbumLinksChange: (List<String>) -> Unit,
    onHeaderLinkChange: (String) -> Unit,

){
    Column {

        TextFieldAddNameComponent(name, onAddNameChange)
        TextFieldAddDescriptionComponent(description, onDescriptionChange)
        TextFieldAddStyleComponent(style, onStyleChange)
        TextFieldAddRecordLabelComponent(recordLabel, onRecordLabelChange)
        TextFieldAddComponentsComponent(components, onComponentsChange)
        TextFieldAddAlbumLinksComponent(
            albumLinks = albumLinks,
            onAlbumLinksChange = { newString ->
                val list = newString.split(",").map { it.trim() }
                onAlbumLinksChange(list)
            }
        )
        TextFieldAddHeaderLinkComponent(headerLink, onHeaderLinkChange)
    }
}
@Composable
fun TextFieldAddNameComponent(name: String, onAddNameChange: (String) -> Unit) {

    // Comprueba si el email es válido

    OutlinedTextField(
        value = name,
        onValueChange = onAddNameChange,
        singleLine = true,
        label = {
            Text("banda", color = MaterialTheme.colorScheme.secondary)
        },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}
@Composable
fun TextFieldAddDescriptionComponent(
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text("Descripción") }
    )
}

@Composable
fun TextFieldAddStyleComponent(
    style: String,
    onStyleChange: (String) -> Unit
) {
    OutlinedTextField(
        value = style,
        onValueChange = onStyleChange,
        singleLine = true,
        label = { Text("Estilo") }
    )
}

@Composable
fun TextFieldAddRecordLabelComponent(
    recordLabel: String,
    onRecordLabelChange: (String) -> Unit
) {
    OutlinedTextField(
        value = recordLabel,
        onValueChange = onRecordLabelChange,
        singleLine = true,
        label = { Text("Discográfica") }
    )
}

@Composable
fun TextFieldAddComponentsComponent(
    components: String,
    onComponentsChange: (String) -> Unit
) {
    OutlinedTextField(
        value = components,
        onValueChange = onComponentsChange,
        label = { Text("Componentes") }
    )
}

@Composable
fun TextFieldAddAlbumLinksComponent(
    albumLinks: List<String>,
    onAlbumLinksChange: (String) -> Unit
) {
    OutlinedTextField(
        value = albumLinks.joinToString(", "), // List → String
        onValueChange = onAlbumLinksChange,    // recibe String
        label = { Text("Links álbum (separados por coma)") }
    )
}

@Composable
fun TextFieldAddHeaderLinkComponent(
    headerLink: String,
    onHeaderLinkChange: (String) -> Unit
) {
    OutlinedTextField(
        value = headerLink,
        onValueChange = onHeaderLinkChange,
        label = { Text("Header link") }
    )
}