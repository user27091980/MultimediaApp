package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.session.SessionManager

class UsersInfoRepo(context: Context) {

    private val session = SessionManager(context)
    private val loginRepo = LoginRepo() // Repo simulado de LoginDTO

    companion object {
        val usersInfo = ArrayList<UsersInfoDTO>(
            listOf(
                UsersInfoDTO("0", "user1", "aaaa@gmail.com", "England", "Paco", "Smith"),
                UsersInfoDTO("1", "user2", "bbbb@gmail.com", "USA", "Pedro", "Sánchez"),
                UsersInfoDTO("2", "user3", "cccc@gmail.com", "Spain", "Perico", "Tercero"),
                UsersInfoDTO("3", "user4", "dddd@gmail.com", "Germany", "Adolf", "Gütemberg")
            )
        )
        var currId = 4
    }

    //CRUD básico sobre UsersInfoDTO
    fun readAll(onSuccess: (List<UsersInfoDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(usersInfo.toList())
    }

    fun create(
        user: UsersInfoDTO,
        onSuccess: (UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {
        val newUser = user.copy(id = currId++.toString())
        if (usersInfo.add(newUser)) onSuccess(newUser) else onError()
    }

    fun read(id: String, onSuccess: (UsersInfoDTO?) -> Unit, onError: () -> Unit) {
        val user = usersInfo.find { it.id == id }
        if (user != null) onSuccess(user) else onError()
    }

    fun update(
        updatedUser: UsersInfoDTO,
        onSuccess: (UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {
        val index = usersInfo.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            usersInfo[index] = updatedUser
            onSuccess(updatedUser)
        } else onError()
    }

    fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit) {
        if (usersInfo.removeIf { it.id == id }) onSuccess() else onError()
    }

    // LOGIN usando LoginRepo simulado
    suspend fun login(email: String, password: String): Result<UsersInfoDTO> {
        return try {
            var foundUser: LoginDTO? = null
            loginRepo.readAll(
                onSuccess = { users ->
                    foundUser = users.find { it.email == email && it.pass == password }
                },
                onError = { }
            )

            if (foundUser != null) {
                val userInfo = UsersInfoDTO(
                    id = foundUser!!.id,
                    user = foundUser!!.user,
                    email = foundUser!!.email,
                    country = "",
                    name = "",
                    surname = ""
                )
                session.saveUser(userInfo)
                Result.success(userInfo)
            } else {
                Result.failure(Exception("Usuario o contraseña incorrectos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // REGISTRO usando LoginRepo simulado
    suspend fun register(user: String, email: String, password: String): Result<UsersInfoDTO> {
        return try {
            var newUserDTO: LoginDTO? = null
            val loginUser = LoginDTO(id = "", email = email, user = user, pass = password)

            loginRepo.create(loginUser,
                onSuccess = { newUserDTO = it },
                onError = { throw Exception("Error al registrar usuario") }
            )

            val userInfo = UsersInfoDTO(
                id = newUserDTO!!.id,
                user = newUserDTO!!.user,
                email = newUserDTO!!.email,
                country = "",
                name = "",
                surname = ""
            )
            session.saveUser(userInfo)
            Result.success(userInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getLoggedUser(): UsersInfoDTO? = session.getUser()
    fun logout() = session.logout()
}
