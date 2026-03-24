package com.example.multimediaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.retrofit.RetrofitModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar BandDTO usando BandsRepo.
 *
 * - Se encarga de la lógica de negocio.
 * - Expone los datos a la UI mediante StateFlow (reactivo).
 * - Maneja operaciones CRUD de bandas.
 */
class BandVM : ViewModel() {

    private val repository = BandsRepo(RetrofitModule.bandApi)

    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())
    val bandsState = _bandsState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadAllBands() {
        viewModelScope.launch {
            try {
                val bands = repository.getBands()
                _bandsState.value = bands
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun createBand(band: BandDTO) {
        viewModelScope.launch {
            try {
                repository.createBand(band)

                // 👇 IMPORTANTE: recargar lista
                loadAllBands()

            } catch (e: Exception) {
                _error.value = "Error creando banda"
            }
        }
    }
}
/*
Un ViewModel es una pieza clave de la arquitectura Android (especialmente en MVVM)
que sirve como puente entre la UI y los datos.
Un ViewModel es una clase que:
Guarda y gestiona datos de la UI
Sobrevive a cambios como rotación de pantalla
Separa la lógica de la interfaz
Es el “cerebro” de tu pantalla.
 */