package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.UserCardsComponent
import com.example.multimediaapp.viewmodel.vm.UserInfoVM


/**
 * @author Andrés
 * @param optional modifier Modifier for the external layout customization
 *
 */
@Composable
fun UserInfoScreenPage(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    // Cargar datos al iniciar la pantalla
    LaunchedEffect(Unit) {
        vm.loadData()
    }
    // Observar el estado del ViewModel
    val uiState = vm.uiState.collectAsState()

   /* Column() {
        UserCardsComponent(


        )
    }*/

}
