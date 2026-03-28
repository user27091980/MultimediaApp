package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.entity.LoginEntity
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.entity.toEntity
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.LoginRequestDTO
import com.example.multimediaapp.network.LoginApiService
import java.io.IOException

/**
 * Interfaz que define el contrato del repositorio de login.
 *
 * Permite desacoplar la implementación de la lógica de autenticación
 * de la capa de presentación y facilita la creación de pruebas unitarias.
 */
interface ILoginRepo {

    /**
     * Realiza el login de un usuario mediante su nombre de usuario/email y contraseña.
     *
     * @param name Nombre de usuario o correo electrónico
     * @param passwd Contraseña
     * @return [LoginEntity] con la información del usuario autenticado
     * @throws Exception si ocurre un error de red o credenciales incorrectas
     */
    suspend fun login(name: String, passwd: String): LoginDTO
}

/**
 * Implementación del repositorio de login usando Retrofit.
 *
 * Este repositorio se encarga de:
 * 1. Enviar las credenciales al servidor mediante [LoginApiService].
 * 2. Procesar la respuesta del servidor.
 * 3. Convertir los datos recibidos en un objeto [LoginEntity] para la capa de presentación.
 *
 * @property api Servicio Retrofit para realizar peticiones de login.
 */
class LoginRepo(
    private val api: LoginApiService
): ILoginRepo {

    /**
     * Ejecuta la operación de login.
     *
     * Lanza excepciones si ocurre un error de red o si las credenciales son incorrectas.
     *
     * @param name Nombre de usuario o correo electrónico
     * @param passwd Contraseña
     * @return [LoginEntity] con los datos del usuario autenticado
     * @throws Exception en caso de error de red o credenciales incorrectas
     */
    override suspend fun login(name: String, passwd: String): LoginDTO {
        try {
            // Construye el DTO de request con las credenciales
            val response = api.loginUser(LoginRequestDTO(name, passwd))

            // Verifica si la respuesta fue exitosa
            if (response.isSuccessful) {
                val body = response.body()
                    ?: throw Exception("Respuesta vacía del servidor")
                // Convierte el DTO recibido a la entidad del dominio
                return body.toDTO()
            } else {
                throw Exception("Usuario o contraseña incorrecta")
            }

        } catch (e: IOException) {
            // Captura errores de red
            throw Exception("Error de red", e)
        } catch (e: Exception) {
            // Captura cualquier otro error
            throw Exception(e.message ?: "Error desconocido", e)
        }
    }
}