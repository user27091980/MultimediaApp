package com.example.multimediaapp.session

import android.content.Context
import com.example.multimediaapp.model.UsersInfoDTO

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUser(user: UsersInfoDTO) {
        prefs.edit()
            .putString("user_id", user.id)
            .putString("username", user.user)
            .putString("email", user.email)
            .apply()
    }

    fun getUser(): UsersInfoDTO? {
        val id = prefs.getString("id", null) ?: return null
        val username = prefs.getString("username", "") ?: ""
        val email = prefs.getString("email", "") ?: ""
        return UsersInfoDTO(
            id, username, email,
            country = TODO(),
            name = TODO(),
            surname = TODO()
        )
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}
