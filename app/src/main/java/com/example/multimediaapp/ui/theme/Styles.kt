package com.example.multimediaapp.ui.theme

import androidx.compose.foundation.layout.padding
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
    .padding(8.dp)//internal padding

//value para el etilo de texto en los botones
val styleButtonText = TextStyle(

    fontSize = 15.sp,
    textAlign = TextAlign.Center,
    color = Color.White

)