package com.example.multimediaapp.view.components

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class PicCatcher : ComponentActivity() {


}

@Composable
fun FloatCamera(modifier: Modifier=Modifier, onClick: () -> Unit ) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(Icons.Filled.AddAPhoto, "Sacar foto")
    }
}