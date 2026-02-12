package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersRepo
import com.example.multimediaapp.viewmodel.uistate.UserListUIState
import com.example.multimediaapp.viewmodel.uistate.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserListPageVM : ViewModel(){
    private val _uiState = MutableStateFlow(UserListUIState())
    val uiState: StateFlow<UserListUIState> = _uiState.asStateFlow()
    val repo: UsersRepo = UsersRepo()

    fun loadDatos() {
        _uiState.value = UserListUIState()
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
            _uiState.value = UserListUIState(it.map {
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