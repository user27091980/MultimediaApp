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
                MainDTO("0","Aphex Twin","http://10.0.2.2:5097/ApiGenerica/data/resources/aphx5.jpg"),
                MainDTO("1","Auterchre","http://10.0.2.2:5097/ApiGenerica/data/resources/ae1.jpg"),
                MainDTO(
                    "2",
                    "Boards of Canada",
                    imageBand = "http://10.0.2.2:5097/ApiGenerica/data/resources/boc0.jpg"
                ),
                MainDTO(
                    "3",
                    "Nine inch Nails",
                    imageBand = "http://10.0.2.2:5097/ApiGenerica/data/resources/nin1.jpg"
                ),
                MainDTO(
                    "4",
                    "Tool",
                    imageBand = "http://10.0.2.2:5097/ApiGenerica/data/resources/tool1.jpg"
                ),
            )
        }
    }
}

