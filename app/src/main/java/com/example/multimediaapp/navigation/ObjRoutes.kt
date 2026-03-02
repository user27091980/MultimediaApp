package com.example.multimediaapp.navigation

object ObjRoutes {
    const val LOGINREG = "LoginRegRoute/{id}"
    fun LoginRegScreen(id: String) = "LoginRegRoute/$id"

    const val LOGIN = "LoginRoute/{id}"
    fun LoginScreen(id: String) = "LoginRoute/$id"

    const val REGISTER="RegisterRoute/{id}"
    fun RegisterScreen(id: String) = "RegisterRoute/$id"

    const val MAINSCREEN = "MainScreenRoute/{id}"
    fun MainScreen(id: String) = "MainScreenRoute/$id"

    const val INFOUSER="UserInfoRoute/{id}"
    fun UserInfoScreen(id: String) = "UserInfoRoute/$id"

    const val SETTINGS="SettingsRoute/{id}"
    fun SettingsScreen(id: String) = "SettingsRoute/$id"


}