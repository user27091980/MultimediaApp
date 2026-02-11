package com.example.multimediaapp.data.repositorio

import com.example.multimediaapp.model.BandDTO

class BandRepo {

    companion object {
        val bands = ArrayList<BandDTO>(
            listOf(
                BandDTO(0, "Tool", "Metal", "bgm"),
                BandDTO(1, "Aphex Twin", "idm", "Warp Records"),
                BandDTO(2, "NIN", "industrial", "null corp"),
                BandDTO(3, "Autechre", "idm", "Warp Records")
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
        onSuccess: (estudianteCreado: BandDTO) -> Unit,
        onError: () -> Unit
    ) {

        bands.add(est.copy(id = currId++))
        onSuccess(bands.last())

    }

    fun read(
        id: Int,
        onSuccess: (createdBand: BandDTO?) -> Unit,
        onError: () -> Unit
    ) {

        onSuccess(bands.find { it.id == id })
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