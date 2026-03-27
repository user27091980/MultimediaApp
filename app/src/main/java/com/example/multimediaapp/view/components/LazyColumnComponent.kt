package com.example.multimediaapp.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.multimediaapp.model.MainDTO

/**
 * CardList:
 *
 * Composable que muestra una lista vertical de tarjetas, cada una con:
 * - Imagen de la banda (main.imageBand)
 * - Nombre de la banda (main.bandName)
 *
 * Proporciona interactividad al permitir clics sobre la imagen que llaman a un lambda externo.
 *
 * @param main Lista de objetos MainDTO que contienen los datos de cada banda.
 * @param onImageClick Lambda que se ejecuta al hacer clic en la imagen de una banda.
 */
@Composable
fun CardList(
    main: List<MainDTO>,
    onImageClick: (MainDTO) -> Unit
) {
    // LazyColumn: lista vertical optimizada que renderiza solo los elementos visibles
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // espacio entre tarjetas
    ) {
        // Log para depuración, muestra cuántas bandas se están dibujando
        Log.d("CARD_LIST", "Dibujando ${main.size} bandas")

        // items: itera sobre la lista de bandas
        items(main) { main ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // centra el contenido horizontalmente
            ) {
                // AsyncImage: carga imagen de URL de manera asíncrona usando Coil
                AsyncImage(
                    model = main.imageBand,
                    contentDescription = main.bandName, // accesibilidad
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable { onImageClick(main) }, // callback al hacer click
                    contentScale = ContentScale.Crop, // recorta la imagen si no coincide con la proporción
                    placeholder = ColorPainter(Color.LightGray), // color de carga mientras se descarga la imagen
                    error = ColorPainter(Color.Red) // color de error si no se carga la imagen
                )
                // Nombre de la banda
                Text(
                    text = main.bandName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp)) // separación visual entre elementos
            }
        }
    }
}

/*
Notas de implementación:

1. LazyColumn:
   - Lista vertical optimizada para grandes cantidades de elementos.
   - Renderiza solo los elementos visibles, mejorando rendimiento.

2. items(main):
   - Itera sobre la lista de bandas (MainDTO).
   - Cada elemento representa una tarjeta con imagen y nombre.

3. Column:
   - Organiza la imagen y el nombre verticalmente.
   - Contenido centrado horizontalmente.

4. AsyncImage (Coil):
   - Carga imágenes desde URL de manera asíncrona.
   - placeholder: color gris mientras se carga.
   - error: color rojo si la imagen falla al cargar.
   - contentScale: Crop para ajustar proporciones.

5. clickable:
   - Permite que la imagen responda a clicks y ejecute el callback `onImageClick`.

6. Spacer:
   - Añade separación visual entre tarjetas.

7. Log:
   - Útil para depuración, confirma que Compose está recibiendo los elementos.

8. MaterialTheme:
   - Usa esquema de colores y tipografía consistente con el tema de la app.

Este Composable es ideal para pantallas que muestran listas de bandas o álbumes, como MainScreen.
*/