// Define el paquete donde se encuentra esta clase.
// Forma parte de la capa "data", específicamente en "entity".
package com.example.multimediaapp.data.entity
// Importa el modelo de dominio (DTO) al que se convertirá esta entidad.
import com.example.multimediaapp.model.BandDTO
// Importa la anotación SerializedName de Gson,
// que permite mapear nombres del JSON a propiedades Kotlin.
import com.google.gson.annotations.SerializedName

/*
Representa un modelo de datos simple.
Kotlin generará automáticamente:
- equals()
- hashCode()
- toString()
- copy()

Es ideal para:
- Parseo de JSON (API REST)
- Mostrar datos en RecyclerView
- Persistencia con Room
 */

// Data class que representa una banda musical dentro de la capa de datos.
data class BandEntity(
    // Identificador único de la banda
    val id: String,
    // El campo "nombre" del JSON se mapeará a la propiedad name
    @SerializedName("nombre") val name: String,
    // El campo "texto" del JSON se mapeará a textInfo
    @SerializedName("texto") val textInfo: String,
    // Imagen de cabecera (header) de la banda
    @SerializedName("cabecera") val headerImage: String,
    // Estilo musical de la banda
    @SerializedName("estilo") val style: String,
    // Discográfica de la banda
    @SerializedName("discográfica") val recordLabel: String,
    // Componentes o integrantes del grupo
    @SerializedName("componentes") val components: String,
    // Lista con los nombres de los discos (discografía)
    @SerializedName("discografía") val discography: List<String>,
    // Lista con las URLs o rutas de las imágenes de los discos
    @SerializedName("discos") val albumImages: List<String>,
    // enlaces discos
    @SerializedName("albumLinks") val albumLinks: List<String>,
    //enlace para la imagen de cabecera.
    @SerializedName("enlaces") val headerLink: String

)


// FUNCIÓN DE EXTENSIÓN (Mapper)

// Convierte un BandEntity (capa data)
// en un BandDTO (capa dominio o presentación).
// Esto ayuda a mantener separadas las capas de la arquitectura.
fun BandEntity.toDTO(): BandDTO = BandDTO(
    // Se copian todas las propiedades al DTO
    id = id,
    name = name,
    textInfo = textInfo,
    headerImage = headerImage,
    albumImages = albumImages,
    style = style,
    recordLabel = recordLabel,
    components = components,
    discography = discography,
    albumLinks = albumLinks,
    headerLink = headerLink

)

/**
 * EXPLICACIÓN CODIGO:
 *
 * ¿Qué es BandEntity?
 *
 * BandEntity es una data class que representa el modelo de datos tal como viene desde una fuente externa (normalmente una API o JSON).
 *
 * Está ubicada en:
 *
 * data/entity
 *
 *
 * Lo que significa que pertenece a la capa de datos (Data Layer).
 * ¿Qué significa cada parte?
 * data class
 *
 * Al usar data class, Kotlin genera automáticamente:
 *
 * equals()
 *
 * hashCode()
 *
 * toString()
 *
 * copy()
 *
 * Esto la hace ideal para:
 *
 * Modelos de API
 *
 * RecyclerView
 *
 * Room
 *
 * Transferencia de datos
 *
 * @SerializedName
 *
 * Ejemplo:
 *
 * @SerializedName("nombre") val name: String
 *
 *
 * Esto significa:
 *
 * Si en el JSON viene:
 *
 * {
 *   "nombre": "Tool"
 * }
 *
 *
 * Se guardará en:
 *
 * name = "Tool"
 *
 *
 * Sirve para mapear nombres del JSON (en español) a propiedades Kotlin (en inglés).
 *
 * Se usa con Gson.
 *
 * ¿Qué representa cada propiedad?
 * Propiedad	Qué representa
 * id	Identificador único
 * name	Nombre de la banda
 * textInfo	Descripción o biografía
 * headerImage	Imagen de cabecera
 * style	Estilo musical
 * recordLabel	Discográfica
 * components	Miembros del grupo
 * discography	Lista de discos
 * albumImages	Imágenes de los álbumes
 * imageBand	Imagen principal
 * ¿Por qué existe BandEntity si ya tienes BandDTO?
 *
 * Porque en arquitectura moderna se separan capas:
 *
 * DATA (Entity)  →  DOMAIN (DTO)  →  UI
 *
 *BandEntity
 *
 * Representa cómo vienen los datos desde la API.
 *
 * BandDTO
 *
 * Es el modelo que usa la app internamente.
 *
 * Conversión a DTO (Mapper)
 * fun BandEntity.toDTO(): BandDTO
 *
 *
 * Esto permite:
 *
 * No acoplar la UI al formato del servidor
 *
 * Cambiar la API sin romper la app
 *
 * Mantener Clean Architecture
 *
 * Arquitectura visual
 * API (JSON)
 *    ↓
 * BandEntity  ← Gson
 *    ↓
 * Mapper (toDTO)
 *    ↓
 * BandDTO
 *    ↓
 * ViewModel
 *    ↓
 * UI
 *
 * En resumen
 *
 * BandEntity:
 *
 * ✔ Representa datos crudos de la API
 * ✔ Usa @SerializedName para mapear JSON
 * ✔ Pertenece a la capa data
 * ✔ Se convierte a BandDTO para usarlo en la app
 * ✔ Es ideal para Retrofit + Gson
 */