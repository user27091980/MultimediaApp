package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.viewmodel.uistate.BandListUiState
import com.example.multimediaapp.viewmodel.uistate.BandUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BandVM : ViewModel() {

    private val _uiState = MutableStateFlow(BandListUiState())
    val uiState: StateFlow<BandListUiState> = _uiState.asStateFlow()
    val repo: BandsRepo = BandsRepo()

    fun loadDatos() {
        _uiState.value = BandListUiState()
    }

    fun del(id: Int) {
        repo.delete(id.toString(), onSuccess = {
            loadData()
        }) {
            //DELETE ON ERROR
        }
    }
    fun loadData() {
        repo.readAll({
            _uiState.value = BandListUiState(it.map {
                BandUiState(
                    it.id,
                    it.name,
                    it.textInfo,
                    it.headerImage,
                    it.albumImages,
                    it.style,
                    it.recordLabel,
                    it.components,
                    it.discography,
                    it.imageBand
                )
            }.toList())
        }) {
            //READLL ON ERROR
        }
    }
}
