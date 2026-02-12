package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.viewmodel.uistate.BandListUIState
import com.example.multimediaapp.viewmodel.uistate.BandUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BandListPageVM : ViewModel() {

    class PaginaListaClaseVM : ViewModel() {

        private val _uIState = MutableStateFlow(BandListUIState())
        val uiState: StateFlow<BandListUIState> = _uIState.asStateFlow()
        val repo: BandsRepo = BandsRepo()

        fun loadDatos() {
            _uIState.value = BandListUIState()
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
                _uIState.value = BandListUIState(it.map {
                    BandUIState(
                        it.id,
                        it.name,
                        it.textInfo,
                        it.headerImage,
                        it.imageAlbums,
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
}