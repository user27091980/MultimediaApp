package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.MainDTO

class MainRepo {

    companion object {

        private var curId = 5
        private val mainBands = ArrayList<MainDTO>(
            listOf(
                MainDTO("0", imageBand = "data/resources/aphx5.jpg"),
                MainDTO("1", imageBand = "data/resources/ae1.jpg"),
                MainDTO("2", imageBand = "data/resources/boc0.jpg"),
                MainDTO("3", imageBand="data/resources/nin1.jpg"),
                MainDTO("4", imageBand="data/resources/tool1.jpg"),

                )
        )
    }
}