package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Componente principal del formulario para crear o editar una banda.
 *
 * @param name Nombre de la banda.
 * @param description Descripción de la banda.
 * @param style Estilo musical de la banda.
 * @param recordLabel Discográfica de la banda.
 * @param components Componentes o integrantes de la banda.
 * @param albumLinks Lista de enlaces de álbumes.
 * @param headerLink Enlace principal o destacado.
 * @param onAddNameChange Callback que se ejecuta cuando cambia el nombre.
 * @param onDescriptionChange Callback que se ejecuta cuando cambia la descripción.
 * @param onStyleChange Callback que se ejecuta cuando cambia el estilo.
 * @param onRecordLabelChange Callback que se ejecuta cuando cambia la discográfica.
 * @param onComponentsChange Callback que se ejecuta cuando cambian los componentes.
 * @param onAlbumLinksChange Callback que se ejecuta cuando cambian los links de álbum.
 * @param onHeaderLinkChange Callback que se ejecuta cuando cambia el header link.
 */
@Composable
fun TextFieldAdd(

    //Valores actuales de los campos (estado)
    name: String,
    description: String,
    style: String,
    recordLabel: String,
    components: String,
    albumLinks: List<String>,
    headerLink: String,

    //Callbacks para actualizar cada campo
    onAddNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onStyleChange: (String) -> Unit,
    onRecordLabelChange: (String) -> Unit,
    onComponentsChange: (String) -> Unit,
    onAlbumLinksChange: (List<String>) -> Unit,
    onHeaderLinkChange: (String) -> Unit,
){
    Column {

        //Campo: nombre de la banda
        TextFieldAddNameComponent(name, onAddNameChange)

        //Campo: descripción
        TextFieldAddDescriptionComponent(description, onDescriptionChange)

        //Campo: estilo musical
        TextFieldAddStyleComponent(style, onStyleChange)

        //Campo: discográfica
        TextFieldAddRecordLabelComponent(recordLabel, onRecordLabelChange)

        //Campo: componentes de la banda
        TextFieldAddComponentsComponent(components, onComponentsChange)

        //Campo: links de álbum
        TextFieldAddAlbumLinksComponent(
            albumLinks = albumLinks,

            //Convierte String → List<String>
            onAlbumLinksChange = { newString ->
                val list = newString.split(",").map { it.trim() }
                onAlbumLinksChange(list)
            }
        )

        //Campo: link principal
        TextFieldAddHeaderLinkComponent(headerLink, onHeaderLinkChange)
    }
}

/**
 * Campo de texto para el nombre de la banda.
 *
 * @param name Nombre actual de la banda.
 * @param onAddNameChange Callback que se ejecuta cuando cambia el texto.
 */
@Composable
fun TextFieldAddNameComponent(name: String, onAddNameChange: (String) -> Unit) {

    // 🟡 Campo de texto con estilo personalizado
    OutlinedTextField(
        value = name, // valor actual
        onValueChange = onAddNameChange, // actualización del estado
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

/**
 * Campo de texto para la descripción de la banda.
 *
 * @param description Descripción actual.
 * @param onDescriptionChange Callback para actualizar la descripción.
 */
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

/**
 * Campo de texto para el estilo musical.
 *
 * @param style Estilo actual.
 * @param onStyleChange Callback para actualizar el estilo.
 */
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

/**
 * Campo de texto para la discográfica.
 *
 * @param recordLabel Discográfica actual.
 * @param onRecordLabelChange Callback para actualizar la discográfica.
 */
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

/**
 * Campo de texto para la imagen de la banda mediante URL.
 *
 * @param imageBand URL de la imagen de la banda.
 * @param onImageBandChange Callback para actualizar la URL.
 */
// TODO()
@Composable
fun TextFieldAddImageBandComponent(
    imageBand: String,
    onImageBandChange: (String) -> Unit
) {
    OutlinedTextField(
        value = imageBand,
        onValueChange = onImageBandChange,
        label = { Text("Imagen de la banda (URL)") }
    )
}


/**
 * Campo de texto para los componentes de la banda.
 *
 * @param components Componentes actuales.
 * @param onComponentsChange Callback para actualizar los componentes.
 */
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

/**
 * Campo de texto para los links de álbum.
 *
 * @param albumLinks Lista de enlaces de álbum.
 * @param onAlbumLinksChange Callback que recibe el texto y lo transforma en lista.
 */
@Composable
fun TextFieldAddAlbumLinksComponent(
    albumLinks: List<String>,
    onAlbumLinksChange: (String) -> Unit
) {
    OutlinedTextField(
        value = albumLinks.joinToString(", "), // 🔄 List → String para mostrar
        onValueChange = onAlbumLinksChange,    // recibe texto del usuario
        label = { Text("Links álbum (separados por coma)") }
    )
}

/**
 * Campo de texto para el link principal o header.
 *
 * @param headerLink Link principal actual.
 * @param onHeaderLinkChange Callback para actualizar el link.
 */
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
/*
 * Este archivo define un formulario en Jetpack Compose para crear o editar
 * información de una banda.
 *
 * COMPONENTE PRINCIPAL:
 *
 * TextFieldAdd(...)
 *
 * - Es un formulario compuesto por múltiples campos de texto.
 * - Recibe todos los datos como parámetros (estado).
 * - Utiliza callbacks para actualizar el estado desde el ViewModel.
 *
 * ARQUITECTURA:
 *
 * - Este enfoque sigue el patrón de "state hoisting":
 *   - El estado NO se guarda dentro del composable.
 *   - Se recibe desde fuera (ViewModel).
 *   - Se actualiza mediante callbacks.
 *
 * - Esto hace que los componentes sean:
 *   - Reutilizables
 *   - Testeables
 *   - Independientes del estado
 *
 * CAMPOS DEL FORMULARIO:
 *
 * - name → nombre de la banda
 * - description → descripción
 * - style → estilo musical
 * - recordLabel → discográfica
 * - components → integrantes
 * - albumLinks → lista de enlaces (convertidos a String y viceversa)
 * - headerLink → enlace principal
 *
 * CONVERSIÓN IMPORTANTE:
 *
 * albumLinks:
 * - Se muestra como String: joinToString(", ")
 * - Se convierte a lista al guardar:
 *   split(",").map { it.trim() }
 *
 * COMPONENTES INDIVIDUALES:
 *
 * Cada campo está separado en su propio composable:
 * - TextFieldAddNameComponent
 * - TextFieldAddDescriptionComponent
 * - TextFieldAddStyleComponent
 * - TextFieldAddRecordLabelComponent
 * - TextFieldAddComponentsComponent
 * - TextFieldAddAlbumLinksComponent
 * - TextFieldAddHeaderLinkComponent
 *
 * BENEFICIOS DE ESTA ESTRUCTURA:
 *
 * - Código modular y organizado
 * - Reutilización de componentes
 * - Separación de responsabilidades
 * - Fácil mantenimiento y escalabilidad
 *
 * UI:
 *
 * Column:
 * - Organiza todos los campos verticalmente.
 *
 * OutlinedTextField:
 * - Campo de texto con borde (Material Design).
 * - Permite entrada de datos del usuario.
 *
 * PERSONALIZACIÓN:
 *
 * - Se pueden modificar colores usando MaterialTheme.
 * - Se pueden añadir validaciones en cada campo.
 * - Se pueden extender fácilmente nuevos campos.
 *
 * NOTA:
 *
 * - El estado (name, description, etc.) debe venir desde un ViewModel.
 * - Este composable solo se encarga de la UI.
 */