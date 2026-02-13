package com.example.multimediaapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.viewmodel.uistate.BandUiState

@Composable
fun BandHeader(band: BandUiState) {

    Image(
        painter = rememberAsyncImagePainter(band.headerImage),
        contentDescription = "Banner de ${band.name}",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}







