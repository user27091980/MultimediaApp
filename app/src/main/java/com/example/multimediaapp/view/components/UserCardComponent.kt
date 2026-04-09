package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.ui.theme.cardModifier

/**
 * UserCardComponent:
 *
 * Muestra la información del perfil del usuario (ID, Email y Nombre)
 * utilizando los estilos predefinidos del tema.
 */
@Composable
fun UserCardComponent(

    email: String,
    name: String,
) {
    // Contenedor principal con el modificador de caja del tema
    Box(boxModifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Tarjeta para el Nombre
            Card(cardModifier) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nombre:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Tarjeta para el Email
            Card(cardModifier) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = email,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


/*
Notas importantes:

1. Box:
   - Contenedor que permite centrar la Column y aplicar `boxModifier` (padding, tamaño, fondo, etc.)

2. Column:
   - Organiza los elementos verticalmente y centra horizontalmente con Alignment.CenterHorizontally.

3. Card:
   - Permite encapsular cada dato dentro de un contenedor con estilo, elevación y borde opcional.
   - Mejora la legibilidad y el diseño visual.

4. Text:
   - Muestra la información de usuario con estilo definido por MaterialTheme.
   - Color y tipografía se toman del tema global de la app.

5. boxModifier y cardModifier:
   - Modificadores definidos en el tema de UI para aplicar padding, bordes redondeados, sombras, etc.
   - Facilita mantener consistencia de diseño en toda la app.
*/