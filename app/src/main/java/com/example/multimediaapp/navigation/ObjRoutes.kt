package com.example.multimediaapp.navigation

object ObjRoutes {
    const val LOGINREG = "LoginRegRoute/{id}"
    fun LoginRegScreen(id: String) = "LoginRegRoute/$id"

    const val LOGIN = "LoginRoute/{id}"
    fun LoginScreen(id: String) = "LoginRoute/$id"

    const val REGISTER = "RegisterRoute/{id}"
    fun RegisterScreen(id: String) = "RegisterRoute/$id"

    const val MAIN = "MainRoute/{id}"
    fun MainScreen(id: String) = "MainRoute/$id"

    const val INFOUSER = "UserInfoRoute/{id}"
    fun UserInfoScreen(id: String) = "UserInfoRoute/$id"

    const val SETTINGS = "SettingsRoute/{id}"
    fun SettingsScreen(id: String) = "SettingsRoute/$id"

    const val BAND = "BandRoute/{id}"
    fun BandScreen(id: String) = "BandRoute/$id"

}