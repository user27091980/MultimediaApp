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
 * @param optional modifier Modifier for the external layout customization
 *
 */
@Composable
fun UserInfoScreenPage(
    userInfoId: String,
    vm: UserInfoVM = viewModel()
) {
    // Observar estado con lifecycle awareness
    val uiState by vm.uiState.collectAsStateWithLifecycle()


    // Cargar datos cuando cambie el userInfoId
    LaunchedEffect(userInfoId) {
        vm.loadData(userInfoId)
    }
    // Observar el estado del ViewModel

    Column {
        UserCardComponent(
            user = uiState.user // Ajusta según tu modelo
        )
    }

}
