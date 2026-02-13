package com.example.multimediaapp.view.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.view.components.CardList
import com.example.multimediaapp.viewmodel.vm.BandVM

@Composable
fun MainPage(vm: BandVM = viewModel()) {

    // Carga los datos solo una vez al entrar en la pantalla
    LaunchedEffect(Unit) {
        vm.loadData()
    }
    val uiState = vm.uiState.collectAsState()

    CardList(
        bands = uiState.value.bands
    )
}