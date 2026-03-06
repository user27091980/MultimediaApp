package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.MultimediaApiService
import com.example.multimediaapp.viewmodel.vm.MainVM

class MainRepo(private val api: MultimediaApiService) {

    suspend fun getBands(): List<MainDTO> {
        return try {
            api.getImagesMain() // endpoint que devuelve List<MainDTO>
        } catch (e: Exception) {
            // fallback a datos locales
            listOf(
                MainDTO("0","Autechre","http://localhost:5131/images/ae1"),
                MainDTO("1","Aphex Twin","http://localhost:5131/images/aphx5"),
                MainDTO(
                    "2",
                    "Boards of Canada",
                    imageBand = "http://localhost:5131/images/boc0"
                ),
                MainDTO(
                    "3",
                    "Nine inch Nails",
                    imageBand = "http://localhost:5131/images/nin1"
                ),
                MainDTO(
                    "4",
                    "Tool",
                    imageBand = "http://localhost:5131/images/tool1.jpg"
                ),
            )
        }
    }
}

