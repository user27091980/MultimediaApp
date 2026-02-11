package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.model.BandDTO

class BandsRepo {

    companion object {
        val bands = ArrayList<BandDTO>(
            listOf(
                BandDTO(0, "Tool"),
                BandDTO(1, "Aphex Twin"),
                BandDTO(2, "NIN"),
                BandDTO(3, "Autechre")
            )
        )
        var currId = 4
    }

    //crud

    fun readAll(onSuccess: (List<BandDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(bands)
    }

    fun crear(
        est: BandDTO,
        onSuccess: (bandCreado: BandDTO) -> Unit,
        onError: () -> Unit
    ) {

        if (bands.add(est.copy(id = currId++)))
            onSuccess(bands.last())
        else
            onError()

    }

    fun read(
        id: Int,
        onSuccess: (createdBand: BandDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val band = bands.find { it.id == id }
        if (band != null)
            onSuccess(band)
        else
            onError()
    }

    fun delete(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (bands.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }
}



