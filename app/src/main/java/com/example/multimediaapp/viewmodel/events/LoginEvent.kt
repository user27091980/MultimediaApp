package com.example.multimediaapp.viewmodel.events

// Sealed class que representa todos los eventos posibles del Login.
// Al ser "sealed", solo puede tener subclases definidas en este mismo archivo.
// Esto permite usar "when" sin necesidad de un "else".
sealed class LoginEvent {

    // "data object" define un singleton (una sola instancia)
    // que además tiene comportamiento similar a un data class
    // (mejor implementación de toString(), equals(), etc.).
    //
    // Este evento indica que se debe navegar a la pantalla Home.
    // No contiene datos porque solo representa una acción.
    data object NavigateToHome : LoginEvent()
}