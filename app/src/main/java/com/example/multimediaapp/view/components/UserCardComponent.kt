package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.ui.theme.cardModifier

/**
 * UserCardComponent:
 *
 * Composable que representa una tarjeta de usuario mostrando su información.
 * Cada dato (ID, email, país, nombre y apellido) se organiza dentro de Cards
 * para que tengan estilo uniforme y resalten visualmente.
 *
 * @param id Identificador único del usuario.
 * @param email Correo electrónico del usuario.
 * @param country País de residencia del usuario.
 * @param name Nombre real del usuario.
 * @param surname Apellido del usuario.
 *
 * Organización visual:
 * - Box: contenedor principal que permite centrar la tarjeta y aplicar estilo global.
 * - Column: organiza los elementos de información de forma vertical.
 * - Text: muestra la información del usuario con tipografía y color definidos por MaterialTheme.
 * - Card: encapsula cada dato (email, país, nombre, apellido) para resaltarlos visualmente.
 */
@Composable
fun UserCardComponent(
    id: String,
    email: String,
    country: String,
    name: String,
    surname: String
) {
    // Box como contenedor principal
    Box(boxModifier) {
        // Columna centrada que organiza la información verticalmente
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto del ID del usuario (fuera de Cards para diferenciar)
            Text(
                text = id,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // Card para el email del usuario
            Card(cardModifier) {
                Text(
                    text = email,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Card para el país del usuario
            Card(cardModifier) {
                Text(
                    text = country,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Card para el nombre del usuario
            Card(cardModifier) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Card para el apellido del usuario
            Card(cardModifier) {
                Text(
                    text = surname,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
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