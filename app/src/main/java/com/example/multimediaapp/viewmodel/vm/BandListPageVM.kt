package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repositorio.BandRepo
import com.example.multimediaapp.viewmodel.uistate.BandListUIState
import com.example.multimediaapp.viewmodel.uistate.BandUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BandListPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(BandListUIState())

    //
    val uiState: StateFlow<BandListUIState> = _uiState.asStateFlow()

    //inyección de dependencias.
    val repo: BandRepo = BandRepo()

    fun load() {
        _uiState.value = BandListUIState()

    }

    fun loadData() {
        repo.readAll({
            _uiState.value = BandListUIState(
                it.map
                { BandUIState(it.id, it.name, it.style, it.discography) }.toList()
            )
        }) {

        }
    }

    fun delete(id: Int) {
        repo.delete(id, onSuccess = {
            loadData()
        }) {

        }
    }
}