package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.UserInfoListUiState
import com.example.multimediaapp.viewmodel.uistate.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserInfoVM : ViewModel() {

    private val _uiState = MutableStateFlow(UserInfoListUiState())
    val uiState: StateFlow<UserInfoListUiState> = _uiState.asStateFlow()
    val repo: UsersInfoRepo = UsersInfoRepo()

    fun addUser(user: UserInfoUiState) {
        val currentList = _uiState.value.userInfo.toMutableList()
        currentList.add(user)
        _uiState.value = UserInfoListUiState(currentList)
    }

    fun updateUser(updatedUser: UserInfoUiState) {
        val updatedList = _uiState.value.userInfo.map {
            if (it.id == updatedUser.id) updatedUser else it
        }
        _uiState.value = UserInfoListUiState(updatedList)
    }

    fun deleteUser(userId: String) {
        val filteredList = _uiState.value.userInfo.filter {
            it.id != userId
        }
        _uiState.value = UserInfoListUiState(filteredList)
    }

    fun getUserById(userId: String): UserInfoUiState? {
        return _uiState.value.userInfo.find { it.id == userId }
    }

    fun loadData() {
        repo.readAll({
            _uiState.value = UserInfoListUiState(it.map {
                UserInfoUiState(
                    it.id,
                    it.email,
                    it.user,
                    it.country,
                    it.name,
                    it.surname,

                    )
            }.toList())
        }) {
            //READLL ON ERROR
        }
    }
}