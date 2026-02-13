package com.example.multimediaapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.multimediaapp.viewmodel.uistate.BandUiState

@Composable
fun ImagesRowList(band: BandUiState, modifier: Modifier = Modifier) {

    LazyRow(modifier = modifier) {

        items(band.albumImages) { albumImage ->

            Image(
                painter = rememberAsyncImagePainter(albumImage),
                contentDescription = "album image",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop,
            )

        }
    }
}



