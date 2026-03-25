package com.example.multimediaapp.model

/**
 * Data class que representa la información principal de una banda
 * para mostrar en la pantalla MainScreen.
 *
 * Cada objeto corresponde a una banda con su nombre y una imagen asociada.
 */
data class MainDTO(
    // Identificador único de la banda
    val id: String,
    val bandName: String,
    // imagen de la banda
    val imageBand: String
)
/*
 * Este archivo define el modelo MainDTO, que representa la información
 * principal de una banda utilizada en la pantalla principal (MainScreen).
 *
 * Su objetivo es proporcionar los datos mínimos necesarios para mostrar
 * una lista de bandas en la interfaz de usuario.
 *
 * Contiene:
 * - id: identificador único de la banda
 * - bandName: nombre de la banda
 * - imageBand: imagen representativa de la banda
 *
 * Este modelo pertenece a la capa de dominio (Model Layer) y se utiliza
 * como objeto de transferencia de datos (DTO).
 *
 * DTO significa Data Transfer Object, y su función es transportar datos
 * entre las distintas capas de la aplicación sin depender directamente
 * de la estructura de la API o de librerías externas.
 *
 * Ventajas de usar este modelo:
 * - Mantiene la separación entre capas
 * - Facilita la adaptación a cambios en la API
 * - Mejora la organización y mantenimiento del código
 * - Permite controlar qué datos se muestran en la UI
 *
 * Al ser una data class de Kotlin, se generan automáticamente métodos
 * como equals(), hashCode(), toString() y copy().
 *
 * Además, al usar val, los datos son inmutables, lo que evita cambios
 * accidentales y mejora la seguridad de la aplicación.
 */