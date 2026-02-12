package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.RegisterListUiState
import com.example.multimediaapp.viewmodel.uistate.RegisterUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterVM : ViewModel(){

    // Estado privado mutable
    private val _uiState = MutableStateFlow(RegisterListUiState())

    // Estado público inmutable
    val uiState: StateFlow<RegisterListUiState> = _uiState.asStateFlow()

    // Agregar usuario
    fun addRegister(id:String, user: String, pass: String) {
        val currentList = _uiState.value.regUser.toMutableList()

        val newId:String = (if (currentList.isEmpty()) 1 else currentList.maxOf { it.id } + 1) as String

        val newRegister = RegisterUIState(
            id = newId,
            user = user,
            pass = pass
        )

        currentList.add(newRegister)

        _uiState.value = RegisterListUiState(regUser = currentList)
    }

    // Eliminar usuario por id
    fun removeRegister(id: String) {
        val updatedList = _uiState.value.regUser.filter { it.id != id }
        _uiState.value = RegisterListUiState(regUser = updatedList)
    }

    // Limpiar lista
    fun clearRegisters() {
        _uiState.value = RegisterListUiState()
    }
}