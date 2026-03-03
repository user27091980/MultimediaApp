package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.ui.theme.cardModifier
import com.example.multimediaapp.ui.theme.colModifier
import com.example.multimediaapp.ui.theme.styleText
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState

@Composable
fun UserCardComponent(id: String,
                      email: String,
                      country: String,
                      user: String,
                      name: String,
                      surname: String) {

    Box(boxModifier) {
        Column(colModifier) {

            Text(
                text = id, style = styleText
            )

            //fila priemra
            Card(cardModifier) {
                Text(
                    text = email, style = styleText
                )
            }
            //fila priemra
            Card(
                cardModifier
            ) {

                Text(
                    text = country,
                    style = styleText
                )

            }

            //fila priemra
            Card(cardModifier) {

                Text(
                    text = user,
                    style = styleText,
                )
            }

            //fila priemra
            Card(cardModifier) {

                Text(
                    text = name,
                    style = styleText,
                )
            }

            Card(cardModifier) {

                Text(
                    text = surname,
                    style = styleText,
                )
            }
        }
    }
}