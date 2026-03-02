package com.example.multimediaapp.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.UserCardComponent
import com.example.multimediaapp.viewmodel.vm.UserInfoVM


/**
 * @author Andrés
 * @param external layout customization
 *
 */
@Composable
fun UserInfoScreen(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    // Observar estado con lifecycle awareness
    val uiState = vm.uiState.collectAsState()


    // Cargar datos cuando cambie el userInfoId
    LaunchedEffect(userInfoId) {
        vm.loadData(userInfoId)
    }
    // Observar el estado del ViewModel

    Column {
        UserCardComponent(

            name = uiState.name
        )
    }

}
