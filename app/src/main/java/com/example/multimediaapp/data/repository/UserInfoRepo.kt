// Paquete donde se encuentra el repositorio.
// Forma parte de la capa data dentro del módulo repository.
package com.example.multimediaapp.data.repository
// Importa el modelo de datos que representa la información de usuario.
import com.example.multimediaapp.model.UsersInfoDTO

// Clase repositorio que gestiona los datos de usuarios.
// Actualmente funciona en memoria (no usa base de datos real).
class UsersInfoRepo {
    // Companion object → variables estáticas compartidas
    // entre todas las instancias del repositorio.
    companion object {
        // Lista mutable que almacena los usuarios.
        // Se inicializa con algunos datos de prueba (hardcoded).
        val usersInfo = ArrayList<UsersInfoDTO>(
            listOf(
                UsersInfoDTO("0", "aaaa@gmail.com", "user1", "England", "Paco", "Smith"),
                UsersInfoDTO("1", "bbbb@gmail.com", "user2", "USA", "Pedro", "Sánchez"),
                UsersInfoDTO("2", "cccc@gmail.com", "user3", "Spain", "Perico", "Tercero"),
                UsersInfoDTO("3", "dddd@gmail.com", "user4", "Germany", "Adolf", "Gütemberg")
            )
        )

        // Variable que guarda el siguiente ID disponible.
        // Se incrementa cada vez que se crea un usuario nuevo.
        var currId = 4

    }

    // -------------------------------------------------
    // CRUD
    // -------------------------------------------------
    // READ ALL
    // Devuelve todos los usuarios.
    fun readAll(onSuccess: (List<UsersInfoDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(usersInfo.toList())//evitamos que se pueda modificar desde fuera
    }

    // CREATE
    // Crea un nuevo usuario.
    fun crear(
        est: UsersInfoDTO,
        onSuccess: (usuarioCreado: UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {
        // Se crea una copia del usuario recibido,
        // asignándole un nuevo ID autoincremental.
        val newUser = est.copy(id = currId++.toString())
        // Si se añade correctamente a la lista → éxito
        if (usersInfo.add(newUser))
            onSuccess(newUser)
        else
            onError()

    }

    // READ (por ID)
    // Busca un usuario por su ID.
    fun read(
        id: String,
        onSuccess: (UsersInfoDTO?) -> Unit,
        onError: () -> Unit
    ) {
        // Busca el primer usuario cuyo id coincida.
        val user = usersInfo.find { it.id == id }
        if (user != null)
            onSuccess(user)
        else
            onError()
    }

    // DELETE
    // Elimina un usuario por ID.
    fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        // removeIf devuelve true si eliminó algún elemento.
        if (usersInfo.removeIf { it.id == (id) })
            onSuccess()
        else
            onError()
    }

    //UPDATE
    //Actualiza usuario por ID

    fun update(
        updatedUser: UsersInfoDTO,
        onSuccess: (UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {
        // Buscamos la posición del usuario con el mismo ID
        val index = usersInfo.indexOfFirst { it.id == updatedUser.id }

        if (index != -1) {
            // Reemplazamos el usuario antiguo por el nuevo
            usersInfo[index] = updatedUser
            onSuccess(updatedUser)
        } else {
            onError()
        }
    }

}