package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.model.UsersDTO

class UsersRepo {
    companion object {
        val users = ArrayList<UsersDTO>(
            listOf(
                UsersDTO(0, "aaaa@gmail.com", "user1","1234"),
                UsersDTO(1,"bbbb@gmail.com","user2","1234"),
                UsersDTO(2,"cccc@gmail.com","user3","1234"),
                UsersDTO(3,"dddd@gmail.com","user4","1234")
            )
        )
        var currId = 4

    }

    //crud

    fun readAll(onSuccess: (List<UsersDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(users.toList())//evitamos que se pueda modificar desde fuera
    }

    fun crear(
        est: UsersDTO,
        onSuccess: (usuarioCreado: UsersDTO) -> Unit,
        onError: () -> Unit
    ) {

        val newUser = est.copy(id = currId++)
        if (users.add(newUser))
            onSuccess(newUser)
        else
            onError()

    }

    fun read(
        id: Int,
        onSuccess: (UsersDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val user = users.find { it.id == id }
        if (user != null)
            onSuccess(user)
        else
            onError()
    }

    fun delete(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (users.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }
}