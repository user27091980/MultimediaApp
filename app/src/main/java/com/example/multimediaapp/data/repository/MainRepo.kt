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
class MainRepo {

    /**
     * Companion object usado para almacenar los datos de forma estática
     * (simula un "cache" o datos locales).
     */
    companion object {
        // Lista de bandas que sirve como fallback si la API falla o para pruebas
        private val mainBands = ArrayList<MainDTO>(
            // fallback a datos locales
            // Cada MainDTO tiene:
            // 1. id: identificador único
            // 2. bandName: nombre de la banda
            // 3. imageUrl: URL de la imagen de la banda
            listOf(
                MainDTO("0", bandName = "Tool", "http://10.0.2.2:5131/images/tool1"),
                MainDTO("1", bandName = "Aphex Twin", "http://10.0.2.2:5131/images/aphx5"),
                MainDTO("2", bandName = "Nine inch Nails", "http://10.0.2.2:5131/images/nin1"),
                MainDTO("3", bandName = "Autechre", "http://10.0.2.2:5131/images/ae1"),
                MainDTO("4", bandName = "Boards of Canada", "http://10.0.2.2:5131/images/boc0"),
            )
        )
    }
    /**
     * Devuelve la lista de bandas al ViewModel o a quien lo solicite.
     *
     * Aunque aquí los datos son locales, la función podría ser `suspend`
     * si en el futuro se conecta a una API con Retrofit.
     */
    fun getMainImages(): List<MainDTO> {
        return mainBands
    }
}


