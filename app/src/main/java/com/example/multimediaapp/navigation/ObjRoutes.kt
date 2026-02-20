package com.example.multimediaapp.navigation

object ObjRoutes {
    const val LOGINREG = "LoginRegRoute/{id}"
    fun loginReg(id: String) = "LoginRegRoute/$id"

    const val LOGIN = "LoginRoute/{id}"
    fun login(id: String) = "LoginRoute/$id"

    const val REGISTER="RegisterRoute/{id}"
    fun register(id: String) = "RegisterRoute/$id"

    const val MAINSCREEN = "MainScreenRoute/{id}"
    fun mainScreen(id: String) = "MainScreenRoute/$id"

    const val INFOUSER="UserInfoRoute/{id}"
    fun infoUser(id: String) = "UserInfoRoute/$id"

    const val SETTINGS="SettingsRoute/{id}"
    fun SettingsPage(id: String) = "SettingsRoute/$id"


}