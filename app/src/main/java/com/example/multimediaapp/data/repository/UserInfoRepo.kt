package com.example.multimediaapp.data.repository

import android.content.Context
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UsersInfoDTO
import com.example.multimediaapp.session.SessionManager
/**
 * Repositorio para manejar la información de los usuarios.
 *
 * Funciones principales:
 * - CRUD sobre UsersInfoDTO.
 * - Registro y login simulados usando LoginRepo.
 * - Manejo de sesión a través de SessionManager.
 *
 * @param context Context necesario para SessionManager
 */
class UsersInfoRepo(context: Context) {
    // Sesión del usuario actual
    private val session = SessionManager(context)
    // Repo simulado de LoginDTO
    private val loginRepo = LoginRepo()

    companion object {
        // Lista simulada de usuarios
        val usersInfo = ArrayList<UsersInfoDTO>(
            listOf(
                UsersInfoDTO("0", "user1", "aaaa@gmail.com", "Paco", "Smith"),
                UsersInfoDTO("1", "user2", "bbbb@gmail.com", "Pedro", "Sánchez"),
                UsersInfoDTO("2", "user3", "cccc@gmail.com", "Perico", "Tercero"),
                UsersInfoDTO("3", "user4", "dddd@gmail.com", "Adolf", "Gütemberg")
            )
        )
        // Contador de IDs para nuevos usuarios
        var currId = 4
    }
    //CRUD básico sobre UsersInfoDTO
    /**
     * Leer todos los usuarios.
     * @param onSuccess Callback con la lista de usuarios.
     * @param onError Callback si ocurre un error.
     */
    fun readAll(onSuccess: (List<UsersInfoDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(usersInfo.toList())
    }
    /**
     * Crear un nuevo usuario.
     * @param user Datos del usuario.
     * @param onSuccess Callback con el usuario creado.
     * @param onError Callback si falla la creación.
     */
    fun create(
        user: UsersInfoDTO,
        onSuccess: (UsersInfoDTO) -> Unit,
        onError: () -> Unit
    ) {
        val newUser = user.copy(id = currId++.toString())
        if (usersInfo.add(newUser)) onSuccess(newUser) else onError()
    }
    /**
     * Leer un usuario por ID.
     * @param id ID del usuario.
     * @param onSuccess Callback con el usuario o null si no existe.
     * @param onError Callback si ocurre un error.
     */
    fun read(id: String, onSuccess: (UsersInfoDTO?) -> Unit, onError: () -> Unit) {
        val user = usersInfo.find { it.id == id }
        if (user != null) onSuccess(user) else onError()
    }
    /**
     * Actualizar un usuario existente.
     * @param updatedUser Usuario con datos actualizados.
     * @param onSuccess Callback con el usuario actualizado.
     * @param onError Callback si no se encuentra el usuario.
     */
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
    /**
     * Eliminar un usuario por ID.
     * @param id ID del usuario a eliminar.
     * @param onSuccess Callback si se elimina correctamente.
     * @param onError Callback si falla la eliminación.
     */
    fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit) {
        if (usersInfo.removeIf { it.id == id }) onSuccess() else onError()
    }

    // LOGIN usando LoginRepo simulado
    /**
     * Autenticar usuario con email y contraseña.
     * @return Result con UsersInfoDTO si es correcto o Exception si falla.
     */
    suspend fun login(email: String, password: String): Result<UsersInfoDTO> {
        return try {
            var foundUser: LoginDTO? = null
            // Buscamos en el repositorio simulado
            loginRepo.readAll(
                onSuccess = { users ->
                    foundUser = users.find { it.email == email && it.pass == password }
                },
                onError = { }
            )

            if (foundUser != null) {
                // Convertimos LoginDTO a UsersInfoDTO
                val userInfo = UsersInfoDTO(
                    id = foundUser!!.id,
                    user = "",
                    email = foundUser!!.email,
                    name = "",
                    surname = ""
                )
                // Guardamos sesión
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
    /**
     * Registrar un nuevo usuario.
     * @param user Nombre de usuario.
     * @param email Email del usuario.
     * @param password Contraseña.
     * @return Result con UsersInfoDTO creado o Exception si falla.
     */
    suspend fun register(user: String, email: String, password: String): Result<UsersInfoDTO> {
        return try {
            var newUserDTO: LoginDTO? = null
            val loginUser = LoginDTO(id = "", email = email, user = user, pass = password)
            // Creamos el usuario en LoginRepo simulado
            loginRepo.create(loginUser,
                onSuccess = { newUserDTO = it },
                onError = { throw Exception("Error al registrar usuario") }
            )
            // Convertimos a UsersInfoDTO y guardamos sesión
            val userInfo = UsersInfoDTO(
                id = newUserDTO!!.id,
                user = newUserDTO!!.user,
                email = newUserDTO!!.email,
                name = "",
                surname = ""
            )
            session.saveUser(userInfo)
            Result.success(userInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // SESIÓN DEL USUARIO
    /**
     * Obtener usuario actualmente logueado.
     */
    fun getLoggedUser(): UsersInfoDTO? = session.getUser()
    /**
     * Cerrar sesión del usuario actual.
     */
    fun logout() = session.logout()
}
