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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.multimediaapp.R
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.view.components.TextFieldAdd
import java.util.UUID

/**
 * Pantalla de registro de una nueva banda.
 *
 * Esta pantalla permite al usuario introducir toda la información necesaria para crear una banda,
 * incluyendo:
 * - Datos básicos (nombre, descripción, estilo, discográfica)
 * - Componentes del grupo
 * - Enlaces de álbum y cabecera
 * - Imágenes (banner, imagen de la banda y galería de álbum)
 *
 * Al completar el formulario y pulsar el botón de registro,
 * se construye un objeto [BandDTO] con todos los datos introducidos
 * y se envía mediante el callback [onRegister].
 *
 * @param onRegister Callback que se ejecuta al registrar la banda.
 * Recibe un objeto [BandDTO] con todos los datos introducidos por el usuario.
 */
@Composable
fun AddBandScreen(
    onRegister: (BandDTO) -> Unit
) {

    // =========================
    // Estados del formulario
    // =========================

    /** Nombre de la banda */
    var name by remember { mutableStateOf("") }

    /** Descripción de la banda */
    var description by remember { mutableStateOf("") }

    /** Estilo musical */
    var style by remember { mutableStateOf("") }

    /** Discográfica */
    var recordLabel by remember { mutableStateOf("") }

    /** Componentes de la banda */
    var components by remember { mutableStateOf("") }

    /** Enlaces de álbum */
    var albumLinks by remember { mutableStateOf(listOf<String>()) }

    /** Enlace principal */
    var headerLink by remember { mutableStateOf("") }

    // =========================
    // Estados de imágenes
    // =========================

    /** Imagen principal (banner) */
    var bannerImage by remember { mutableStateOf<Uri?>(null) }

    /** Imagen de la banda */
    var imageBand by remember { mutableStateOf<Uri?>(null) }

    /** Galería de imágenes del álbum */
    var albumImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // =========================
    // Estado de error
    // =========================

    /** Mensaje de error de validación */
    var error by remember { mutableStateOf<String?>(null) }

    // =========================
    // Launchers de imágenes
    // =========================

    val bannerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        bannerImage = uri
    }

    val bandImageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        imageBand = uri
    }

    val albumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        albumImages = uris
    }

    // =========================
    // UI
    // =========================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /**
         * Título de la pantalla.
         */
        Text(
            text = stringResource(R.string.bandRegister),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Botón para seleccionar imagen de la banda.
         */
        Button(
            onClick = { bandImageLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.selBand))
        }

        /**
         * Vista previa de la imagen de la banda.
         */
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

        /**
         * Botón para seleccionar imágenes del álbum.
         */
        Button(
            onClick = { albumLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.selAlbum))
        }

        Spacer(modifier = Modifier.height(10.dp))

        /**
         * Vista previa de las imágenes del álbum.
         */
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
         * Formulario de datos de la banda.
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

        /**
         * Mensaje de error si existe.
         */
        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        /**
         * Botón de registro.
         *
         * Valida los campos obligatorios y crea un objeto [BandDTO].
         */
        Button(
            onClick = {

                if (name.isBlank() || description.isBlank()) {
                    error = "Nombre y descripción son obligatorios"
                    return@Button
                }

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

                onRegister(bandDTO)

                // Reset del formulario
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
 * Vista previa de la pantalla en Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddBandScreenPreview() {
    AddBandScreen(onRegister = {})
}