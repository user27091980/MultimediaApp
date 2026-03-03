package com.example.multimediaapp.view.components

// Layouts básicos de Compose
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
// Componente visual tipo Card
import androidx.compose.material3.Card
// Composable para mostrar texto
import androidx.compose.material3.Text
// Composable para mostrar texto
import androidx.compose.runtime.Composable
// Modifiers y estilos personalizados definidos en el tema de la app
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.ui.theme.cardModifier
import com.example.multimediaapp.ui.theme.colModifier
import com.example.multimediaapp.ui.theme.styleText
// UI State del usuario (no se usa en este Composable, pero útil para ViewModel)
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
/**
 * Composable que muestra la información del usuario en tarjetas
 *
 * @param id Identificador único del usuario
 * @param email Correo electrónico
 * @param country País de residencia
 * @param user Nombre de usuario o nickname
 * @param name Nombre real
 * @param surname Apellido
 */
@Composable
fun UserCardComponent(id: String,
                      email: String,
                      country: String,
                      user: String,
                      name: String,
                      surname: String) {
// 🔹 Caja principal que puede contener elementos superpuestos o controlar padding/margen
    Box(boxModifier) {
        // 🔹 Columna para colocar los elementos uno debajo del otro
        Column(colModifier) {
            // 🔹 Muestra el ID del usuario

            Text(
                text = id, style = styleText
            )

            // 🔹 Tarjeta para el email
            Card(cardModifier) {
                Text(
                    text = email, style = styleText
                )
            }
            // 🔹 Tarjeta para el país
            Card(
                cardModifier
            ) {

                Text(
                    text = country,
                    style = styleText
                )

            }

            // 🔹 Tarjeta para el usuario
            Card(cardModifier) {

                Text(
                    text = user,
                    style = styleText,
                )
            }

            // 🔹 Tarjeta para el nombre
            Card(cardModifier) {

                Text(
                    text = name,
                    style = styleText,
                )
            }
            // 🔹 Tarjeta para el apellido
            Card(cardModifier) {

                Text(
                    text = surname,
                    style = styleText,
                )
            }
        }
    }
}