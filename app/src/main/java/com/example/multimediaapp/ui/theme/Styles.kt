package com.example.multimediaapp.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

val styleText = TextStyle(
    fontSize = 15.sp,
    textAlign = TextAlign.Center,
    color = Color.White
)

val colModifier = Modifier
    .padding(20.dp, 130.dp, 20.dp, 110.dp)
val boxModifier = Modifier
    .fillMaxSize()
    .padding(5.dp, 30.dp, 5.dp, 90.dp)

//row para la imagen de cabecera
val rowModifier =Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 8.dp)

//row para las cards de info
val rowCardModifier = Modifier
    .padding(10.dp, 140.dp, 10.dp, 180.dp)

//row para el lazyRow
val lazyRowMod = Modifier
    .padding(5.dp, 600.dp, 5.dp, 5.dp)

//modificadores para cards
val cardModifier = Modifier
    .size(width = 300.dp, height = 100.dp)
    .padding(15.dp)

//modificador para las cards del mainScreen.
val lazyColumnCardModifier = Modifier
    .padding(5.dp, 10.dp, 5.dp, 10.dp)

//value para el posicionamiento del icono de la cámara.
val cameraMod = Modifier.padding(300.dp, 720.dp, 10.dp, 10.dp)