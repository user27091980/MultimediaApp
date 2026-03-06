package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.viewmodel.vm.MainVM

class MainRepo(viewModel: MainVM) {

    companion object {

        private val mainBands = ArrayList<MainDTO>(
            listOf(
                MainDTO(
                    "0",
                    "Aphex Twin",
                    imageBand = "/Users/anders/StudioProjects/ApiGenerica/data/resources/aphx5.jpg"
                ),
                MainDTO(
                    "1",
                    "auterchre",
                    imageBand = "/Users/anders/StudioProjects/ApiGenerica/data/resources/ae1.jpg"
                ),
                MainDTO(
                    "2",
                    "Boards of Canada",
                    imageBand = "/Users/anders/StudioProjects/ApiGenerica/data/resources/boc0.jpg"
                ),
                MainDTO(
                    "3",
                    "Nine inch Nails",
                    imageBand = "/Users/anders/StudioProjects/ApiGenerica/data/resources/nin1.jpg"
                ),
                MainDTO(
                    "4",
                    "Tool",
                    imageBand = "/Users/anders/StudioProjects/ApiGenerica/data/resources/tool1.jpg"
                ),
            )
        )

        fun getBands(): List<MainDTO> {
            return mainBands
        }
    }
}

