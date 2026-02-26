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
fun UserCardComponent(userInfoId: UserInfoUiState) {

    Box(boxModifier) {
        Column(colModifier) {

            Text(
                text = userInfoId.name, style = styleText
            )

            //fila priemra
            Card(cardModifier) {
                Text(
                    text = userInfoId.surname, style = styleText
                )
            }
            //fila priemra
            Card(
                cardModifier
            ) {

                Text(
                    text = userInfoId.email,
                    style = styleText
                )

            }

            //fila priemra
            Card(cardModifier) {

                Text(
                    text = userInfoId.user,
                    style = styleText,
                )
            }

            //fila priemra
            Card(cardModifier) {

                Text(
                    text = userInfoId.country,
                    style = styleText,
                )
            }

            Card(cardModifier) {

                Text(
                    text = userInfoId.email,
                    style = styleText,
                )
            }
        }
    }
}