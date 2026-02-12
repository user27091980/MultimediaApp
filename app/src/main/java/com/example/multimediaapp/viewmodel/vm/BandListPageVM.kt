package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.viewmodel.uistate.BandListUIState
import com.example.multimediaapp.viewmodel.uistate.BandUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BandListPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(BandListUIState())
    val uiState: StateFlow<BandListUIState> = _uiState.asStateFlow()
    val repo: BandsRepo = BandsRepo()

    fun loadDatos() {
        _uiState.value = BandListUIState()
    }

    fun del(id: Int) {
        repo.delete(id, onSuccess = {
            loadData()
        }) {
            //DELETE ON ERROR
        }
    }
    fun loadData() {
        repo.readAll({
            _uiState.value = BandListUIState(it.map {
                BandUIState(
                    it.id,
                    it.name,
                    it.textInfo,
                    it.headerImage,
                    it.albumImages,
                    it.style,
                    it.recordLabel,
                    it.components,
                    it.discography
                )
            }.toList())
        }) {
            //READLL ON ERROR
        }
    }
}
