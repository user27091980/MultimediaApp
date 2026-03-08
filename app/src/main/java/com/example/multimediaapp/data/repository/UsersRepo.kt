package com.example.multimediaapp.data.repository

// Importa el modelo de usuario
import com.example.multimediaapp.model.UsersDTO

/**
 * Repositorio que maneja operaciones CRUD sobre los usuarios.
 *
 * Actualmente utiliza datos en memoria (ArrayList) como "fake database".
 * Puede ser reemplazado por API o base de datos real en el futuro.
 */

class UsersRepo {
    /**
     * Companion object para almacenar los usuarios y el contador de IDs
     * de manera estática, accesible desde cualquier instancia de UsersRepo.
     */
    companion object {
        // Lista en memoria de usuarios iniciales
        val users = ArrayList<UsersDTO>(
            listOf(
                UsersDTO("0", "aaaa@gmail.com", "user1", "1234"),
                UsersDTO("1", "bbbb@gmail.com", "user2", "1234"),
                UsersDTO("2", "cccc@gmail.com", "user3", "1234"),
                UsersDTO("3", "dddd@gmail.com", "user4", "1234")
            )
        )
        var currId = 4

    }

    //crud

    fun readAll(onSuccess: (List<UsersDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(users.toList())//evitamos que se pueda modificar desde fuera
    }

    fun create(
        est: UsersDTO,
        onSuccess: (usuarioCreado: UsersDTO) -> Unit,
        onError: () -> Unit
    ) {

        val newUser = est.copy(id = currId++.toString())
        if (users.add(newUser))
            onSuccess(newUser)
        else
            onError()

    }

    fun read(
        id: String,
        onSuccess: (UsersDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val user = users.find { it.id == (id) }
        if (user != null)
            onSuccess(user)
        else
            onError()
    }

    fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (users.removeIf { it.id == (id) })
            onSuccess()
        else
            onError()
    }
}