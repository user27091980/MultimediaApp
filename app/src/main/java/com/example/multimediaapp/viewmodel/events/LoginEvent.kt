package com.example.multimediaapp.viewmodel.events

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
}