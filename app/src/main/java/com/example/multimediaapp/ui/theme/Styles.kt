package com.example.multimediaapp.ui.theme

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author="Andrés"
 * elementos para aplicar estilo de forma reutilizable
 */
//value para el estilo de texto
val styleText = TextStyle(

    fontSize = 18.sp,
    textAlign = TextAlign.Center,

    )

//value para el etilo de texto en los botones
val styleButtonText = TextStyle(

    fontSize = 15.sp,
    textAlign = TextAlign.Center

)
val styleTextCards = TextStyle(

    fontSize = 45.sp,
    textAlign = TextAlign.Center

)

//modificador para boton
val button = Modifier
    .size(width = 100.dp, height = 100.dp)

//modificador para imagéns
val imageModifier = Modifier
    .fillMaxHeight()
    .fillMaxWidth()
    .size(100.dp)

//modificadores para elementos box, column y row empleados para manejar los padding
val colModifier = Modifier
    .padding(20.dp, 130.dp, 20.dp, 110.dp)
val boxModifier = Modifier
    .fillMaxSize()
    .padding(5.dp, 30.dp, 5.dp, 90.dp)

//row para la imagen de cabecera
val rowModifier = Modifier
    .padding(1.dp, 20.dp, 1.dp, 20.dp)

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