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
import com.example.multimediaapp.view.components.ButtonGallery
import com.example.multimediaapp.view.components.ButtonImage
import com.example.multimediaapp.view.components.TextFieldAdd
import java.util.UUID

/**
 * Pantalla para registrar una banda.
 *
 * @param onRegister función que recibe el objeto BandDTO con los datos introducidos.
 */
@Composable
fun AddBandScreen(
    onRegister: (BandDTO) -> Unit
) {

    // Estado del formulario
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var style by remember { mutableStateOf("") }
    var recordLabel by remember { mutableStateOf("") }
    var components by remember { mutableStateOf("") }
    var albumLinks by remember { mutableStateOf(listOf<String>()) }
    var headerLink by remember { mutableStateOf("") }

    // Estado de imágenes
    var bannerImage by remember { mutableStateOf<Uri?>(null) }
    var imageBand by remember { mutableStateOf<Uri?>(null) }
    var albumImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // Estado de error
    var error by remember { mutableStateOf<String?>(null) }

    /**
     * Layout principal de la pantalla.
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro de Banda",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Selección de imagen para banner.
         */
        ButtonImage(
            text = stringResource(R.string.addBanner),
            onImageSelected = { bannerImage = it }
        )

        bannerImage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = it,
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Selección de imagen de la banda.
         */
        ButtonImage(
            text = stringResource(R.string.addBand),
            onImageSelected = { imageBand = it }
        )

        imageBand?.let {
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = it,
                contentDescription = "Imagen banda",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Selección de múltiples imágenes del álbum.
         */
        ButtonGallery(
            text = stringResource(R.string.addAlbums),
            onImagesSelected = { albumImages = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        albumImages.forEach { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Imagen álbum",
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
         * Mensaje de error.
         */
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
         * Valida campos obligatorios y crea un objeto BandDTO.
         */
        androidx.compose.material3.Button(
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

                // Reset de estado
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
            Text(stringResource(R.string.registro))
        }
    }
}

/**
 * Preview de la pantalla.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddBandScreenPreview() {
    AddBandScreen(
        onRegister = {}
    )
}

/*
 * Esta pantalla permite registrar una nueva banda introduciendo sus datos principales.
 *
 * El usuario puede completar un formulario con información como:
 * - Nombre de la banda
 * - Descripción
 * - Estilo musical
 * - Discográfica
 * - Componentes del grupo
 * - Enlaces de álbum y enlace principal
 *
 * Además, la pantalla permite seleccionar diferentes imágenes:
 * - Una imagen de banner (portada)
 * - Una imagen representativa de la banda
 * - Varias imágenes del álbum
 *
 * Todas las imágenes seleccionadas se almacenan como Uri en estado local
 * y se muestran como vista previa en la pantalla.
 *
 * El formulario está controlado mediante estados de Compose (remember + mutableStateOf),
 * lo que permite actualizar la UI automáticamente al cambiar los valores.
 *
 * Cuando el usuario pulsa el botón de registro:
 * - Se valida que los campos obligatorios estén completos (nombre y descripción).
 * - Se crea un objeto BandDTO con toda la información introducida.
 * - Se envía este objeto mediante el callback onRegister.
 *
 * Finalmente, se limpian todos los campos del formulario y los estados,
 * dejando la pantalla lista para un nuevo registro.
 *
 * En caso de error de validación, se muestra un mensaje en pantalla.
 */