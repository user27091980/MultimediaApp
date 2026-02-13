package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimediaapp.ui.theme.cardColumnModifier
import com.example.multimediaapp.viewmodel.uistate.BandUiState

@Composable
fun CardColumnComponent(
    band: BandUiState
) {

    //Column for organice cards

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        //main card with band text
        Card(
            cardColumnModifier
        ) {

            Text(text = band.textInfo)

        }
    }
}


