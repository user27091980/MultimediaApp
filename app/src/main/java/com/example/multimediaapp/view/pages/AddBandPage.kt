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
import com.example.multimediaapp.data.mapper.toMainDTO
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.view.components.TextFieldAdd
import java.util.UUID

@Composable
fun AddBandScreen(
    onRegister: (BandDTO) -> Unit
) {

    //Estados
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var style by remember { mutableStateOf("") }
    var recordLabel by remember { mutableStateOf("") }
    var components by remember { mutableStateOf("") }
    var albumLinks by remember { mutableStateOf(listOf<String>()) }
    var headerLink by remember { mutableStateOf("") }

    var bannerImage by remember { mutableStateOf<Uri?>(null) }
    var albumImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    var error by remember { mutableStateOf<String?>(null) }

    // 📸 Launcher imagen principal
    val bannerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        bannerImage = uri
    }

    //Launcher múltiples imágenes
    val albumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        albumImages = uris
    }

    //UI
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

        // 📸 Imagen principal
        Button(
            onClick = { bannerLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar imagen principal")
        }

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

        //Álbum
        Button(
            onClick = { albumLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir imágenes del álbum")
        }

        Spacer(modifier = Modifier.height(10.dp))

        albumImages.forEach { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Album image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Formulario
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

        //Error
        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Botón registrar
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

                //Entity (si lo usas)
                val entity = bandDTO.toEntity()

                //MainDTO (para pantalla principal)
                val mainDTO = bandDTO.toMainDTO()

                //enviar banda
                onRegister(bandDTO)

                // 🧹 limpiar formulario
                name = ""
                description = ""
                style = ""
                recordLabel = ""
                components = ""
                albumLinks = emptyList()
                headerLink = ""
                bannerImage = null
                albumImages = emptyList()
                error = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Banda")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddBandScreenPreview() {
    AddBandScreen(
        onRegister = { /* No hace nada en preview */ }
    )
}