// Paquete donde se encuentran los modelos de la aplicación.
// Pertenece a la capa de dominio / modelo.
package com.example.multimediaapp.model
// Data class que representa el modelo que usa la aplicación
// para trabajar con la información de una banda.
data class BandDTO(
    // Identificador único de la banda
    val id: String,
    // Nombre de la banda
    val name: String,
    // Descripción o información detallada de la banda
    val textInfo: String,
    // Imagen principal de cabecera (banner)
    val headerImage: String,
    // Lista de imágenes de los álbumes
    val albumImages: List<String>,
    // Estilo musical
    val style: String,
    // Discográfica
    val recordLabel: String,
    // Componentes o miembros del grupo
    val components: String,
    // Lista de nombres de discos publicados
    val discography: List<String>,
    // Imagen representativa de la banda
    val imageBand: String

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
 *         ↓
 *    Mapper (toDTO)
 *         ↓
 * BandDTO (Model / Domain)
 *         ↓
 * ViewModel / UI
 *
 * 🧠 ¿Por qué usar DTO?
 *
 * ✔ Separa la capa de datos de la UI
 * ✔ Permite cambiar la API sin afectar la interfaz
 * ✔ Evita acoplamiento con Retrofit / Gson
 * ✔ Es más limpio arquitectónicamente
 *
 * 🔎 ¿Por qué es data class?
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
 *
 *
 * Sin modificar el objeto original (inmutabilidad).
 *
 * 🎯 Diferencia con BandEntity
 * BandEntity	BandDTO
 * Representa datos de la API	Representa datos que usa la app
 * Tiene @SerializedName	No tiene anotaciones
 * Vive en data/entity	Vive en model
 * Puede cambiar si cambia la API	Debe ser estable
 */
