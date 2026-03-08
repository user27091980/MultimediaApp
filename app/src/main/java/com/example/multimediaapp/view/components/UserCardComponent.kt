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


@Composable
fun UserCardComponent(id: String,
                      email: String,
                      country: String,
                      user: String,
                      name: String,
                      surname: String) {

    Box(boxModifier) {
        Column(modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = id,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            //fila priemra
            Card (cardModifier){
                Text(
                    text = email,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            //fila priemra
            Card (cardModifier) {

                Text(
                    text = country,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }

            //fila priemra
            Card (cardModifier) {

                Text(
                    text = user,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            //fila priemra
            Card (cardModifier) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Card (cardModifier) {

                Text(
                    text = surname,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}