package com.example.multimediaapp.view.components

// Importaciones necesarias de Compose
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.viewmodel.uistate.BandUiState

@Composable
fun BandTags(band: BandUiState) {

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp) // separación vertical entre filas
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {

            TagItem(band.components)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { TagItem(band.recordLabel) }
        Row()
        { TagItem(band.style) }

        band.discography.forEach { album ->
            TagItem(album)
        }

    }
}


@Composable
fun TagItem(tag: String) {
    Text(
        text = tag,
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = Color.Black
    )
}
