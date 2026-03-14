package com.example.multimediaapp.session

import android.content.Context
import com.example.multimediaapp.model.UsersInfoDTO

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUser(user: UsersInfoDTO) {
        prefs.edit()
            .putString("id", user.id) // Cambiado a "id" para coincidir con la lectura
            .putString("username", user.user)
            .putString("email", user.email)
            .putString("name", user.name)
            .putString("lastName", user.lastName)
            .putString("country",user.country)
            .apply()
    }

    fun getUser(): UsersInfoDTO? {
        val id = prefs.getString("id", null) ?: return null
        val email = prefs.getString("email", "") ?: ""
        val pass = prefs.getString("pass", "") ?: ""
        val user = prefs.getString("username", "") ?: ""
        val name = prefs.getString("name", "") ?: ""
        val lastName = prefs.getString("lastName", "") ?: ""
        val country = prefs.getString("country","")?:""


        return UsersInfoDTO(
            id=id,
            email= email,
            pass = pass,
            user= user,
            name= name,
            country=country,
            lastName = lastName
        )
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isUserLoggedIn(): Boolean {
        return prefs.contains("id")
    }
}
