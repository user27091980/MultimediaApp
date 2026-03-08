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
    user: String,
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

            /// Card para el usuario
            Card(cardModifier) {

                Text(
                    text = user,
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