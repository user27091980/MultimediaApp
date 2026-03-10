package com.example.multimediaapp.data.repository

// Importa el modelo que representa los datos de una banda
import com.example.multimediaapp.model.MainDTO

/**
 * Repositorio encargado de proporcionar los datos para la pantalla principal.
 *
 * Funciona como capa intermedia entre la UI (ViewModel) y los datos reales.
 * En este caso, los datos se generan localmente (fallback) pero podrían venir
 * de un API o base de datos en el futuro.
 */
interface IMainRepo {
    /**
     * Devuelve la lista de bandas disponibles.
     */
    fun getBands(): List<MainDTO>

    /**
     * Devuelve una banda concreta según su id.
     */
    fun getBandById(id: String): MainDTO?
}


