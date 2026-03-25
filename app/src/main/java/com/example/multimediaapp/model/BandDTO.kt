// Paquete donde se encuentran los modelos de la aplicación.
// Pertenece a la capa de dominio / modelo.
package com.example.multimediaapp.model

// Data class que representa el modelo que usa la aplicación
// para trabajar con la información de una banda.
data class BandDTO(
    // Identificador único de la banda
    val id: String,
    val name: String,
    val description: String,
    val banner: String,
    val albumImages: List<String>,
    val style: String,
    val recordLabel: String,
    val components: String,
    val albumLinks: List<String>,
    val headerLink: String
)

/**
 * EXPLICACIÓN:
 *
 * ¿Qué es BandDTO?
 *
 * DTO significa:
 *
 * Data Transfer Object
 *
 * Es un objeto que transporta datos entre capas de la aplicación.
 *
 * En tu arquitectura:
 *
 * BandEntity (Data Layer)
 *
 *    Mapper (toDTO)
 *
 * BandDTO (Model / Domain)
 *
 * ViewModel / UI
 *
 * ¿Por qué usar DTO?
 *
 * Separa la capa de datos de la UI
 * Permite cambiar la API sin afectar la interfaz
 * Evita acoplamiento con Retrofit / Gson
 * Es más limpio arquitectónicamente
 *
 * ¿Por qué es data class?
 *
 * Porque Kotlin genera automáticamente:
 *
 * equals()
 *
 * hashCode()
 *
 * toString()
 *
 * copy()
 *
 * Ejemplo útil:
 *
 * val editedBand = band.copy(name = "Nuevo Nombre")
 *Sin modificar el objeto original (inmutabilidad).
 *
 * Diferencia con BandEntity:
 * BandEntity	BandDTO
 * Representa datos de la API	Representa datos que usa la app
 * Tiene @SerializedName	No tiene anotaciones
 * Vive en data/entity	Vive en model
 * Puede cambiar si cambia la API	Debe ser estable
 */
/*
 * Este archivo contiene el modelo BandDTO, que representa los datos de una banda
 * utilizados dentro de la aplicación.
 *
 * Pertenece a la capa de dominio o modelo (Model Layer), y se utiliza como
 * objeto de transferencia de datos entre las distintas capas de la app.
 *
 * BandDTO contiene toda la información necesaria de una banda, como:
 * - id: identificador único
 * - name: nombre de la banda
 * - description: descripción
 * - banner: imagen principal
 * - albumImages: lista de imágenes del álbum
 * - style: estilo musical
 * - recordLabel: discográfica
 * - components: miembros de la banda
 * - albumLinks: enlaces relacionados
 * - headerLink: enlace principal
 *
 * Este modelo se utiliza principalmente en la capa de presentación (UI)
 * y en la lógica de negocio, evitando depender directamente de la estructura
 * de la API.
 *
 * DTO significa Data Transfer Object, y su función es transportar datos
 * entre las distintas capas de la aplicación.
 *
 * En la arquitectura:
 * - BandEntity (capa de datos)
 * - Mapper (toDTO / toEntity)
 * - BandDTO (capa de modelo)
 * - UI / ViewModel
 *
 * Ventajas de usar DTO:
 * - Desacopla la aplicación de la API
 * - Permite modificar la API sin afectar la UI
 * - Mejora la organización del código
 * - Facilita el mantenimiento
 *
 * Esta clase es una data class de Kotlin, lo que proporciona automáticamente:
 * - equals()
 * - hashCode()
 * - toString()
 * - copy()
 *
 * Además, al usar val en todos sus campos, el objeto es inmutable,
 * lo que evita modificaciones accidentales y mejora la seguridad.
 *
 * Diferencia con BandEntity:
 * - BandEntity representa los datos tal como llegan de la API
 * - BandDTO representa los datos que usa la aplicación
 * - BandEntity puede cambiar si cambia el backend
 * - BandDTO se mantiene estable para la app
 */