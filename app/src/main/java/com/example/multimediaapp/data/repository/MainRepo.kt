package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.MainDTO

class MainRepo : IMainRepo {

    private val bands = mutableListOf<MainDTO>()

    override fun getBands(): List<MainDTO> {
        return bands
    }

    override fun getBandById(id: String): MainDTO? {
        return bands.find { it.id == id }
    }

    fun readAll(onSuccess: (List<MainDTO>) -> Unit, onError: () -> Unit) {
        try {
            onSuccess(bands)
        } catch (e: Exception) {
            onError()
        }
    }

    fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit) {
        try {
            val removed = bands.removeIf { it.id == id }
            if (removed) {
                onSuccess()
            } else {
                onError()
            }
        } catch (e: Exception) {
            onError()
        }
    }
}
