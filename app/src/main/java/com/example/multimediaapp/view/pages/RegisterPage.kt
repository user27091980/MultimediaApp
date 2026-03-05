package com.example.multimediaapp.view.pages

import com.example.multimediaapp.view.components.ButtonAcept
import com.example.multimediaapp.view.components.ButtonCancel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.boxModifier
import com.example.multimediaapp.view.components.TextFieldsComponent
import com.example.multimediaapp.viewmodel.vm.RegisterVM

@Composable
fun RegisterScreen(navController: NavController, vm: RegisterVM = viewModel()) {
    Box(
        modifier = boxModifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Black) // verde Spotify → negro
                )
            )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp, 40.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldsComponent()
            }

            ButtonAcept(
                onClick = { navController.navigate(ObjRoutes.INFOUSER) }
            )
            ButtonCancel(
                onClick = { navController.navigate(ObjRoutes.LOGIN) }
            )


        }

    }

}

@Preview
@Composable
fun RegisterScreenPreview() {

    val navController = rememberNavController()

    RegisterScreen(
        navController = navController
    )
}


