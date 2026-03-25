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
 * Composable que representa una tarjeta (Card) de usuario.
 *
 * Muestra información del usuario en diferentes filas dentro de Cards.
 *
 * @param id Identificador del usuario
 * @param email Correo electrónico del usuario
 * @param country País del usuario
 * @param user Nombre de usuario (nickname)
 * @param name Nombre real del usuario
 * @param surname Apellido del usuario
 */
@Composable
fun UserCardComponent(
    id: String,
    email: String,
    country: String,
    name: String,
    surname: String
) {
    // Box que funciona como contenedor principal de la tarjeta
    // `boxModifier` define estilo global (padding, tamaño, etc.)
    Box(boxModifier) {
        // Columna para organizar la información verticalmente
        // `Modifier.align(Alignment.Center)` centra la columna dentro del Box
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
// Texto del ID del usuario
            Text(
                text = id,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // Card para el email
            Card(cardModifier) {
                Text(
                    text = email,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            // Card para el país
            Card(cardModifier) {

                Text(
                    text = country,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }
            //card para el nombre
            Card(cardModifier) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            //card para el apellido
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
 * Este archivo define un componente de UI en Jetpack Compose
 * que muestra la información de un usuario en formato de tarjeta (Card).
 *
 * COMPONENTE PRINCIPAL:
 *
 * UserCardComponent(...)
 *
 * - Recibe los datos del usuario como parámetros:
 *   - id
 *   - email
 *   - country
 *   - name
 *   - surname
 *
 * - Se encarga únicamente de la presentación (UI).
 * - No gestiona lógica de negocio ni estado.
 *
 * ESTRUCTURA:
 *
 * 1. Box:
 *    - Contenedor principal.
 *    - Permite centrar o superponer elementos.
 *    - Aplica `boxModifier` para estilos globales.
 *
 * 2. Column:
 *    - Organiza los elementos verticalmente.
 *    - Centra el contenido con:
 *      horizontalAlignment = Alignment.CenterHorizontally
 *
 * 3. Text:
 *    - Muestra el ID del usuario.
 *    - Se muestra fuera de una Card (como encabezado).
 *
 * 4. Card:
 *    - Se usa para mostrar cada campo del usuario de forma visualmente separada:
 *      - Email
 *      - País
 *      - Nombre
 *      - Apellido
 *
 *    - Aplica `cardModifier` para estilo común:
 *      - padding
 *      - elevación
 *      - forma
 *
 * UI / MATERIAL DESIGN:
 *
 * MaterialTheme:
 * - Permite aplicar estilos globales de la app.
 * - Se usa para:
 *   - typography.titleMedium → tamaño del texto
 *   - colorScheme.primary → color principal del texto
 *
 * DISEÑO:
 *
 * - Cada dato del usuario se muestra en una Card independiente.
 * - Esto mejora la legibilidad y organización visual.
 * - El contenido está centrado en pantalla.
 *
 * MODIFICADORES:
 *
 * boxModifier:
 * - Define estilo global del contenedor (padding, tamaño, etc.).
 *
 * cardModifier:
 * - Define el estilo de cada Card (espaciado, forma, elevación).
 *
 * BENEFICIOS:
 *
 * - Código modular y reutilizable.
 * - Separación clara entre UI y lógica.
 * - Fácil de mantener y escalar.
 * - UI consistente y alineada con Material Design.
 *
 * NOTA:
 *
 * - Este componente es puramente declarativo.
 * - Los datos deben provenir de un ViewModel o capa superior.
 */