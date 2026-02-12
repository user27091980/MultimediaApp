package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.UsersInfoDTO

class UsersInfoRepo {

    companion object {
        val usersInfo = ArrayList<UsersInfoDTO>(
            listOf(
                UsersInfoDTO("0", "aaaa@gmail.com", "user1","England","Paco","Smith"),
                UsersInfoDTO("1","bbbb@gmail.com","user2","USA","Pedro","Sánchez"),
                UsersInfoDTO("2","cccc@gmail.com","user3","Spain","Perico","Tercero"),
                UsersInfoDTO("3","dddd@gmail.com","user4","Germany","Adolf","Gütemberg")
            )
        )
        var currId = 4

    }

    //crud

    fun readAll(onSuccess: (List<UsersInfoDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(usersInfo.toList())//evitamos que se pueda modificar desde fuera
    }

    fun crear(
        est: UsersInfoDTO,
        onSuccess: (usuarioCreado: UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {

        val newUser = est.copy(id = currId++.toString())
        if (usersInfo.add(newUser))
            onSuccess(newUser)
        else
            onError()

    }

    fun read(
        id: Int,
        onSuccess: (UsersInfoDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val user = usersInfo.find { it.id.equals(id)}
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
        if (usersInfo.removeIf { it.id.equals(id) })
            onSuccess()
        else
            onError()
    }
}