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
 * Composable que muestra una lista vertical de tarjetas con imagen y nombre de banda.
 *
 * @param main Lista de objetos MainDTO que contienen la información de cada banda.
 * @param onImageClick Lambda que se ejecuta al hacer clic en la imagen de una banda.
 */
@Composable
fun CardList(
    main: List<MainDTO>, onImageClick: (MainDTO) -> Unit // Lambda que recibe la banda clicada
) {
    // LazyColumn permite crear listas verticales, cargando solo los elementos visibles.
    // Es útil para listas grandes, para optimizar rendimiento.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Itera sobre la lista de URLs de imágenes de los álbumes.
        // 'imageBand' es cada URL de la lista.
        // Mostramos un log aquí para confirmar que Compose está recibiendo los items
        Log.d("CARD_LIST", "Dibujando ${main.size} bandas")
        items(main) { main ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente todo dentro de la columna
            ) {
                AsyncImage(
                    model = main.imageBand,
                    contentDescription = main.bandName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable { onImageClick(main) },
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.LightGray),
                    error = ColorPainter(Color.Red)
                )
                Text(
                    text = main.bandName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}


/*
 * Este archivo define un componente de UI en Jetpack Compose que muestra
 * una lista vertical de bandas, cada una representada por una imagen y su nombre.
 *
 * COMPONENTE PRINCIPAL:
 *
 * CardList(main: List<MainDTO>, onImageClick: (MainDTO) -> Unit)
 *
 * - Recibe una lista de objetos MainDTO (bandas).
 * - Recibe un callback (onImageClick) que se ejecuta cuando el usuario
 *   hace clic en una imagen.
 *
 * ESTRUCTURA DE LA UI:
 *
 * LazyColumn:
 * - Lista vertical optimizada para rendimiento.
 * - Solo renderiza los elementos visibles en pantalla.
 * - Ideal para listas grandes.
 *
 * - Modifier:
 *   - fillMaxSize(): ocupa toda la pantalla.
 *   - background(): aplica el color de fondo del tema.
 *   - padding(): añade espacio vertical entre elementos.
 *
 * items(main):
 * - Itera sobre la lista de bandas.
 * - Cada elemento de la lista se representa individualmente.
 *
 * Column:
 * - Organiza los elementos de cada ítem en vertical.
 * - Centra el contenido horizontalmente.
 *
 * COMPONENTES INTERNOS:
 *
 * AsyncImage (Coil):
 * - Carga imágenes desde una URL de forma asíncrona.
 * - Optimiza el rendimiento y la gestión de imágenes.
 *
 * - model: URL de la imagen (main.imageBand)
 * - contentDescription: accesibilidad (nombre de la banda)
 * - contentScale = Crop: ajusta la imagen al tamaño recortando si es necesario
 *
 * - placeholder: imagen mientras carga (color gris)
 * - error: imagen si falla la carga (color rojo)
 *
 * clickable:
 * - Permite detectar cuando el usuario toca la imagen.
 * - Ejecuta la función onImageClick pasando la banda seleccionada.
 *
 * Text:
 * - Muestra el nombre de la banda.
 * - Se estiliza con:
 *   - color del tema
 *   - estilo tipográfico grande (headlineMedium)
 *   - negrita (FontWeight.Bold)
 *
 * Spacer:
 * - Añade espacio visual entre elementos para mejorar la UI.
 *
 * LOG:
 * - Log.d("CARD_LIST", ...) permite depurar y verificar que los datos llegan correctamente.
 *
 * CONCEPTOS IMPORTANTES:
 *
 * LazyColumn:
 * - Clave para rendimiento en listas grandes.
 *
 * Composable:
 * - Permite definir UI declarativa en Compose.
 *
 * Coil:
 * - Librería para carga eficiente de imágenes.
 *
 * Callback (onImageClick):
 * - Permite comunicar la interacción del usuario hacia capas superiores.
 *
 * BENEFICIOS:
 * - UI dinámica y eficiente.
 * - Separación de lógica y presentación.
 * - Interactividad con eventos de usuario.
 * - Optimización de rendimiento en listas.
 */