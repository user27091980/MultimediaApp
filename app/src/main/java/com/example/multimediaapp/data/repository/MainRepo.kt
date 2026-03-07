package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.network.MultimediaApiService
import com.example.multimediaapp.viewmodel.vm.MainVM

class MainRepo(private val api: MultimediaApiService) {

    suspend fun getMainImages(): List<MainDTO>{
    return try {
            api.getImagesMain() // endpoint que devuelve List<MainDTO>
        } catch (e: Exception) {
            // fallback a datos locales
            listOf(
                MainDTO("0", "Tool", "http://10.0.2.2:5131/images/tool1"),
                MainDTO("1", "Aphex Twin", "http://10.0.2.2:5131/images/aphx5"),
                MainDTO("2", "Nine inch Nails", "http://10.0.2.2:5131/images/nin1"),
                MainDTO("3", "Autechre", "http://10.0.2.2:5131/images/ae1"),
                MainDTO("4", "Boards of Canada", "http://10.0.2.2:5131/images/boc0"),
            )
        }
    }
}

