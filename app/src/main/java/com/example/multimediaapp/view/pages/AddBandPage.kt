package com.example.multimediaapp.view.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.view.components.TextFieldAdd
import java.util.UUID

/**
 * Pantalla de registro de una nueva banda.
 *
 * Permite al usuario introducir los datos de la banda y seleccionar:
 * - Imagen principal (banner)
 * - Imagen de la banda (logo/avatar)
 * - Imágenes del álbum
 *
 * Una vez completado el formulario, se crea un objeto [BandDTO]
 * y se envía mediante el callback [onRegister].
 *
 * @param onRegister Callback que se ejecuta cuando el usuario registra la banda.
 * Recibe un objeto [BandDTO] con todos los datos introducidos.
 */
@Composable
fun AddBandScreen(
    onRegister: (BandDTO) -> Unit
) {

    // Estados del formulario
    var name by remember { mutableStateOf("") }              // Nombre de la banda
    var description by remember { mutableStateOf("") }       // Descripción
    var style by remember { mutableStateOf("") }             // Estilo musical
    var recordLabel by remember { mutableStateOf("") }       // Discográfica
    var components by remember { mutableStateOf("") }        // Componentes
    var albumLinks by remember { mutableStateOf(listOf<String>()) } // Links de álbum
    var headerLink by remember { mutableStateOf("") }        // Link principal

    // Estados de imágenes
    var bannerImage by remember { mutableStateOf<Uri?>(null) }   // Imagen principal
    var imageBand by remember { mutableStateOf<Uri?>(null) }     // Imagen de la banda
    var albumImages by remember { mutableStateOf<List<Uri>>(emptyList()) } // Galería

    //  Estado de error
    var error by remember { mutableStateOf<String?>(null) }

    //  Selector de imagen para el banner
    val bannerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        bannerImage = uri
    }

    //  Selector de imagen para la banda
    val bandImageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        imageBand = uri
    }

    //Selector de múltiples imágenes (álbum)
    val albumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        albumImages = uris
    }

    /**
     * UI principal de la pantalla.
     *
     * Contiene:
     * - Selección de imágenes
     * - Formulario de datos
     * - Botón de registro
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Título de la pantalla
        Text(
            text = "Registro de Banda",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        //BOTÓN PARA SELECCIONAR BANNER
        Button(
            onClick = { bannerLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar banner (portada)")
        }

        //Vista previa del banner
        bannerImage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = it,
                contentDescription = "Banner de la banda",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //BOTÓN PARA IMAGEN DE LA BANDA
        Button(
            onClick = { bandImageLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar imagen de la banda")
        }

        //Vista previa imagen de la banda
        imageBand?.let {
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = it,
                contentDescription = "Imagen de la banda",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //BOTÓN PARA GALERÍA DE IMÁGENES
        Button(
            onClick = { albumLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir imágenes del álbum")
        }

        Spacer(modifier = Modifier.height(10.dp))

        //Vista previa de la galería
        albumImages.forEach { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Imagen del álbum",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Formulario reutilizable con todos los campos de texto.
         */
        TextFieldAdd(
            name = name,
            description = description,
            style = style,
            recordLabel = recordLabel,
            components = components,
            albumLinks = albumLinks,
            headerLink = headerLink,

            onAddNameChange = { name = it },
            onDescriptionChange = { description = it },
            onStyleChange = { style = it },
            onRecordLabelChange = { recordLabel = it },
            onComponentsChange = { components = it },
            onAlbumLinksChange = { albumLinks = it },
            onHeaderLinkChange = { headerLink = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        //Mensaje de error
        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        /**
         * Botón para registrar la banda.
         *
         * Valida los campos obligatorios y crea un [BandDTO]
         * con todos los datos introducidos.
         */
        Button(
            onClick = {

                // Validación básica
                if (name.isBlank() || description.isBlank()) {
                    error = "Nombre y descripción son obligatorios"
                    return@Button
                }

                // Crear DTO
                val bandDTO = BandDTO(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    description = description,
                    banner = bannerImage?.toString() ?: "",
                    albumImages = albumImages.map { it.toString() },
                    style = style,
                    recordLabel = recordLabel,
                    components = components,
                    albumLinks = albumLinks,
                    headerLink = headerLink
                )

                // Enviar datos a la capa superior
                onRegister(bandDTO)

                // Limpiar formulario
                name = ""
                description = ""
                style = ""
                recordLabel = ""
                components = ""
                albumLinks = emptyList()
                headerLink = ""
                bannerImage = null
                imageBand = null
                albumImages = emptyList()
                error = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Banda")
        }
    }
}

/**
 * Vista previa de la pantalla en modo diseño (Preview).
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddBandScreenPreview() {
    AddBandScreen(
        onRegister = { }
    )
}