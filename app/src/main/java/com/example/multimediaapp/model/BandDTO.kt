// Paquete donde se encuentran los modelos de la aplicación.
// Pertenece a la capa de dominio / modelo.
package com.example.multimediaapp.model

/**
 * Data class que representa el modelo que usa la aplicación
 * para trabajar con la información de una banda.
 *
 * BandDTO se utiliza para transportar datos entre la capa de datos
 * y la capa de presentación (UI / ViewModel) sin acoplar la app a
 * la API o la librería de serialización.
 */
data class BandDTO(
    // Identificador único de la banda
    val id: String,

    // Nombre de la banda
    val name: String,

    // Descripción de la banda
    val description: String,

    // URL del banner principal de la banda
    val banner: String,

    // Lista de URLs de imágenes de álbumes
    val albumImages: List<String>,

    // Estilo musical de la banda
    val style: String,

    // Discográfica
    val recordLabel: String,

    // Componentes / integrantes de la banda
    val components: String,

    // Links a los álbumes de la banda
    val albumLinks: List<String>,

    // Link a la cabecera o página principal de la banda
    val headerLink: String,


)

/**
 * EXPLICACIÓN:
 *
 * ¿Qué es BandDTO?
 *
 * DTO significa Data Transfer Object.
 * Es un objeto que transporta datos entre capas de la aplicación,
 * sin exponer detalles de implementación de la API.
 *
 * Arquitectura típica:
 *
 * BandEntity (Data Layer / API)
 *    └─ Mapper (toDTO)
 * BandDTO (Domain / Model)
 *    └─ ViewModel / UI
 *
 * Ventajas de usar DTO:
 * - Desacopla la UI de la capa de datos / Retrofit / Gson
 * - Facilita cambios en la API sin afectar la interfaz
 * - Permite un modelo de datos estable en la aplicación
 * - Promueve la inmutabilidad y limpieza arquitectónica
 *
 * Por qué usar data class en Kotlin:
 * - Genera automáticamente equals(), hashCode(), toString(), copy()
 * - Permite crear copias modificadas sin alterar el original
 *   Ejemplo:
 *   val editedBand = band.copy(name = "Nuevo Nombre")
 *
 * Diferencias con BandEntity:
 *  - BandEntity: representa los datos tal como vienen de la API,
 *    puede tener anotaciones @SerializedName, vive en data/entity.
 *  - BandDTO: representa los datos que usa la aplicación,
 *    no depende de la librería de serialización, vive en model,
 *    y se mantiene estable incluso si cambia la API.
 */