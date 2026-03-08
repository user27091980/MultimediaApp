package com.example.multimediaapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author="Andrés"
 * elementos para aplicar estilo de forma reutilizable
 */
//value para el estilo de texto

val bandColumnModifier = Modifier
    .padding(16.dp)//internal padding
val cardColumnModifier = Modifier
    .fillMaxWidth()
    .padding(10.dp)
//internal padding

val boxModifier = Modifier
    .fillMaxSize()
    .padding(5.dp, 30.dp, 5.dp, 90.dp)

//row para la imagen de cabecera
val rowModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 8.dp)

//modificadores para cards
val cardModifier = Modifier
    .size(width = 300.dp, height = 100.dp)
    .padding(15.dp)

