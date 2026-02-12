package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersRepo
import com.example.multimediaapp.viewmodel.uistate.UserListUiState
import com.example.multimediaapp.viewmodel.uistate.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserListVM : ViewModel(){
    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()
    val repo: UsersRepo = UsersRepo()

    fun loadDatos() {
        _uiState.value = UserListUiState()
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
            _uiState.value = UserListUiState(it.map {
                UserUIState(
                    it.id,
                    it.user,
                    it.pass
                )
            }.toList())
        }) {
            //READLL ON ERROR
        }
    }
}