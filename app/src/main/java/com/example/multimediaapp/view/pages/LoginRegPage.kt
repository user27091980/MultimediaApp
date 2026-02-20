package com.example.multimediaapp.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multimediaapp.view.components.ButtonLogin
import com.example.multimediaapp.view.components.ButtonRegister
import com.example.multimediaapp.viewmodel.vm.LoginRegVM

/**
 * @author Andrés
 */
@Composable
fun LoginRegScreen(vm: LoginRegVM) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black) // verde Spotify → negro
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(horizontal = 32.dp)

        ){


            ButtonLogin(


                )
            // Botón Registro con estilo tonal

            ButtonRegister(




                )


        }

    }
}

@Preview
@Composable
fun LoginRegPrev() {

    LoginRegScreen()

}